package com.example.hci;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.hci.model.Deck;
import com.example.hci.model.FlashCard;
import com.example.hci.model.User;
import com.example.hci.repositories.DeckRepository;
import com.example.hci.repositories.UserRepository;
import com.example.hci.usecase.CurrentData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class editCard extends AppCompatActivity {
    CurrentData currentData = CurrentData.getInstance();
    UserRepository userRepository = UserRepository.getInstance();
    DeckRepository deckRepository = DeckRepository.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        // https://stackoverflow.com/questions/13377361/how-to-create-adrop-down-list
        Spinner dropdown = findViewById(R.id.spinnerChooseDeck);
        //String[] items = new String[]{};
        List<String> items = new ArrayList<String>();
        User momentaner = userRepository.findById(currentData.getUserId());
        for(Map.Entry<UUID, Deck> entry :
                momentaner.getOwnDecks().entrySet()){
            items.add(entry.getValue().getName());
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        //**BUTTON Zurück**
        Button backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        YourStacksActivity.class);
                startActivity(i);
            }
        });
        //**BUTTON Karte Speichern**
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView frontTextTextView =
                        findViewById(R.id.CardFrontText);
                String frontText = frontTextTextView.getText().toString();
                TextView backTextTextView =
                        findViewById(R.id.cardRearText);
                String backText = backTextTextView.getText().toString();
                FlashCard newFlashcard = new FlashCard(frontText,
                        backText);
                Spinner spinner = findViewById(R.id.spinnerChooseDeck);
                String text = spinner.getSelectedItem().toString();
                Deck choosenDeck = deckRepository.findDeckByName2(text);
                choosenDeck.addFlashCard(newFlashcard);
                //currentDeck.addFlashCard(newFlashcard);
                Intent i = new Intent(getApplicationContext(),
                        YourStacksActivity.class);
                startActivity(i);
            }
        });
    }
}