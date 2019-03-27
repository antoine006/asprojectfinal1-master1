package com.example.hp1.asproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MoviesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Movie> arrayList = new ArrayList<>();
    ListView lvmovies;
    Button btAddToWishList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        lvmovies = findViewById(R.id.lvMovies);

        String category = getIntent().getStringExtra("Category");
        Toast.makeText(this, getIntent().getStringExtra("Category"), Toast.LENGTH_LONG).show();
        if(category != null)
            fillSubCategoriesList(category);

        MovieCustomAdapter adapter = new MovieCustomAdapter(this, R.layout.movie_custom_row, arrayList);
        lvmovies.setAdapter(adapter);
        lvmovies.setOnItemClickListener(this);

        btAddToWishList = findViewById(R.id.btAddToWishList);




    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(this,detailedmovie.class);
        i.putExtra("movie", arrayList.get(position));
        startActivity(i);
    }

    public void fillSubCategoriesList(String Category){

        Toast.makeText(this, Category, Toast.LENGTH_SHORT).show();
        if (Category.equals("sports")) {
            arrayList.add(new Movie("Creed", 4.5, R.drawable.creed, "Creed is a 2015 American sports drama film directed by Ryan Coogler and written by Coogler and Aaron Covington. Both a spin-off and sequel in the Rocky film series,[4][5][6] the film originally starred Joshua Lee but later was replaced by Michael B. Jordan who played as Adonis Johnson Creed, Apollo Creed's son, with Sylvester Stallone reprising the role of Rocky Balboa. It also features Tessa Thompson, Phylicia Rashad, Tony Bellew and Graham McTavish. The film reunites Jordan with Fruitvale Station writer-director Coogler, as well as Wood Harris, with whom Jordan had worked on The Wire"));
            arrayList.add(new Movie("concussion", 4.5, R.drawable.concussion, "Concussion is a 2015 American biographical sports drama film directed and written by Peter Landesman, based on the exposé \"Game Brain\" by Jeanne Marie Laskas, published in 2009 by GQ magazine.[5] Set in 2002, the film stars Will Smith as Dr. Bennet Omalu, a forensic pathologist who fights against the National Football League trying to suppress his research on chronic traumatic encephalopathy (CTE) brain degeneration suffered by professional football players. It also stars Alec Baldwin, Gugu Mbatha-Raw, and Albert Brooks. "));
        }
        if (Category.equals("photography")) {
            arrayList.add(new Movie("Closer", 4.5, R.drawable.closer, "Closer is a 2004 American romantic drama film written by Patrick Marber, based on his award-winning 1997 play of the same name. The film was produced and directed by Mike Nichols and stars Julia Roberts, Jude Law, Natalie Portman, and Clive Owen. The film, like the play on which it is based, has been seen by some as a modern and tragic version of Mozart's opera Così fan tutte, with references to the opera in both the plot and the soundtrack.[2] Owen starred in the play as Dan, the role played by Law in the film. "));
            arrayList.add(new Movie("" +"pecker", 4.5, R.drawable.pecker, "Pecker is a 1998 American comedy-drama film written and directed by John Waters. Like all Waters' films, it was filmed and set in Baltimore; this film was set in the Hampden neighborhood."));
        }

        if (Category.equals("biography")) {
            arrayList.add(new Movie("The Wizard of lies", 4.5, R.drawable.thewizardoflies, "The Wizard of Lies is a 2017 American television drama film directed by Barry Levinson and written by Sam Levinson, Sam Baum, and John Burnham Schwartz,[1] based on the non-fiction book of the same name by Diana B. Henriques. The film stars Robert De Niro as businessman Bernard Madoff, Michelle Pfeiffer as his wife Ruth Madoff, and Alessandro Nivola as their older son Mark Madoff. It aired on HBO on May 20, 2017."));
            arrayList.add(new Movie("the persuit of happyness", 4.5, R.drawable.thepersuitofhappyness, "The Pursuit of Happyness is a 2006 American biographical drama film based on entrepreneur Chris Gardner's nearly one-year struggle being homeless. Directed by Gabriele Muccino, the film features Will Smith as Gardner, a homeless salesman. Smith's son Jaden Smith co-stars, making his film debut as Gardner's son, Christopher Jr. "));
        }
        if (Category.equals("History")) {
            arrayList.add(new Movie("troy", 4.5, R.drawable.troy, "Troy is a 2004 epic period war film written by David Benioff, directed by Wolfgang Petersen and co-produced by units in Malta, Mexico and Britain's Shepperton Studios. The film features an ensemble cast led by Brad Pitt, Eric Bana, and Orlando Bloom."));
            arrayList.add(new Movie("Valkyrie", 4.5, R.drawable.walkiria, "Valkyrie is a 2008 historical thriller film directed and co-produced by Bryan Singer and written by Christopher McQuarrie and Nathan Alexander. The film is set in Nazi Germany during World War II and depicts the 20 July plot in 1944 by German army officers to assassinate Adolf Hitler and to use the Operation Valkyrie national emergency plan to take control of the country."));
        }
        if (Category.equals("action")) {
            arrayList.add(new Movie("sky fall", 4.5, R.drawable.skyfall, "Skyfall is a 2012 spy film, the twenty-third in the James Bond series produced by Eon Productions. The film is the third to star Daniel Craig as the fictional MI6 agent James Bond and features Javier Bardem as Raoul Silva, the villain."));
            arrayList.add(new Movie("spectre", 4.5, R.drawable.spectre, "Spectre is a 2015 spy film, the twenty-fourth in the James Bond film series produced by Eon Productions for Metro-Goldwyn-Mayer and Columbia Pictures. It is the fourth film to feature Daniel Craig as the fictional MI6 agent James Bond, and the second film in the series directed by Sam Mendes following Skyfall. It was written by John Logan, Neal Purvis, Robert Wade and Jez Butterworth."));

        }
        lvmovies = findViewById(R.id.lvMovies);

        MovieCustomAdapter adapter  = new MovieCustomAdapter(this, R.layout.movie_custom_row, arrayList);

        lvmovies.setAdapter(adapter);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}