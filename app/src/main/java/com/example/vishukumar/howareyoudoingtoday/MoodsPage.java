package com.example.vishukumar.howareyoudoingtoday;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MoodsPage extends AppCompatActivity {

    TextView howAreYouQuestion,
            happyTextView,
            anxiousTextView,
            angryTextView,
            demotivatedTextView,
            worthlessTextView,
            sadTextView;
    Typeface helvatica;
    Typeface groBold;
    Typeface montserrat;
    Typeface montserrat_bold;
    Typeface vegur_light;
    Typeface vegur_bold;
    Typeface vegur_regular;

    private String[] happyMessages = {
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
            "Wow! Good to hear that!",
    };

    private String[] anxiousMessages = {
        "I understand that you are anxious because you can’t control this situation, but maybe you could try to focus your energy on what you can control.",
            "Are you OK?",
            "It will pass. Just keep breathing.",
            "I’m sorry you are struggling with this.",
            "It’s OK to feel this way.",
            "It sounds like you’re having a hard time. This APP will just stay with you until you feel okay.",
            "Sorry about that! It will pass. Just keep breathing.",
            "Sorry about that! It will pass. Just keep breathing.",
            "Sorry about that! It will pass. Just keep breathing.",
            "Sorry about that! It will pass. Just keep breathing."

    };

    private String[] angryMessages = {
        "Okay, okay, here’s the first thing. In fact, this is actual something you shouldn’t do. Don’t medicate. Don’t drink or do drugs or do anything else that’s going to intensify your feelings",
            "It's okay to feel angry.Go ahead and feel the anger. I mean, really feel it. Sit still and allow that emotion to wash over you. Allow the tide to come in and close your eyes. Think about it like an ocean of waves. Okay, maybe a tidal wave! But feel it as it comes ashore in your conscious mind. Feel it as it breaches land and really embrace it.",
            "Ask yourself why? Okay, Has it just been a string of things that have happened? Is it stress that built up over the recent days or weeks or months, or even years for that matter? It’s okay that you bowed and cracked. It’s okay that you lost your cool.",
            "What Goes Around Comes Around. Okay, before you go seeking revenge, just know this — what goes around, comes around. Yes, we’ve all heard it before. But it’s true. As much as you might want to go hurt another person, it’s really uncalled for. You don’t need to do it.",
            "There Is Pure Power In Focus. Rather than worry yourself by focusing on negative things, shift your focus. Negativity will beget negative results. While it’s easy to be so angry at someone and want to seek some form of revenge, by staying in that negative space you will only attract more negative things to your life.",
            "Put Yourself In The Other Person’s Shoes. Look, we’ve all made mistakes in the past. What you need to do is realize, first and foremost, that you’re not perfect. Once you realize that you’re not perfect, put yourself in the other person’s shoes. Maybe you’ve done something similar in the past. Maybe you haven’t. Doesn’t matter. Try to think about how they feel.",
            "Forgive But Don’t Forget. However, that doesn’t necessarily mean that we have to forget. So forgive them. They messed up. You’re angry. So what? Why escalate the situation? Fill your heart with love and forgive. Go to church and pray about it if you must. If you’re not religious, then release that energy to the universe. That’s all.",
            "Go To The Gym Or Go For A Run. Okay, so maybe you need to work it off. Go for a run. Go to the gym. Sweat it out. Channel all of that anger into a positive direction by doing something that will benefit your health. Not only will exercising give you physical health, but also mental and emotional health. You’ll feel way better after you’ve worked off all of that aggression. Believe me.",
            "Let At Least 24 Hours Pass Before Responding. You need to give it time. Time will heal all wounds and everything good occurs within a certain frame of time. Don’t fire something off like an email or a message when you’re at the peak of anger. As much as you want to do it, it’s best that you wait. When those emotions and the tide of hate has retreated, then you can respond. And only then.",
            "Watch cat videos on internet. After a turbulent situation, it’s good to just relax and vegetate. It feels good to disconnect and laugh a bit. Don’t take life so seriously. It’s here one moment then gone the next. That’s the fragility of this world. Savor every moment and realize that it could all be gone tomorrow. So don’t be so angry all the time."
    };


    private String[] demotivatedMessages = {
        "The secret to staying motivated is to honestly audit your skills, and the challenges that lie ahead. Sometimes a difficult goal is useful in spurring a person on to do better. We persist because the challenge is personally rewarding.",
            "The fact is that you cannot control what other people do or the way they think. Recognizing that it was mostly your fault will help you analyze what went wrong, helping you avoid that pitfall the next time. Once you have done that, you will be better motivated to move on.",
            "The best way to stay motivated, regarding time, is to repeat to yourself that you are the only one who can control your time. Nobody or nothing else can do that for you.",
            "If you want something you never had, you have to do something you've never done. Stay motivated!",
            "You must tell yourself, 'no matter how hard it is or how hard it gets, you are going to make it",
            "Make it happen, shock everyone... Stay motivated!",
            "You only fail when you stop trying... Go for it. Stay motivated",
            "Your mind is a weapon, keep it loaded... Stay motivated",
            "Worrying means you suffer twice... Don't worry. Stay motivated",
            "You are never too young to build an empire and never too old to chase your dream. Stay motivated",

    };

    private String[] worthlessMessages = {
        "Spend time with people who are good for your mental health",
            "Never lower your worth for worthless people",
            "The first step is you have to say you can",
            "Never give someone an opportunity to waste your time twice",
            "Don't keep allowing the same things to upset you. Life is too short to live that way",
            "Stay away from negative people, they have a problem for every solution",
            "There will always be someone who can't see your worth, don't let it be you",
            "The only thing you are committed to right now is to bettering yourself. Remember that.",
            "Your hardest times often lead to the greatest moments of your life. Keep the faith. It will be all worth it in the end.",
            "Keep smiling, one day life will get tired of upsetting you."

    };

    private String[] sadMessages = {
            "It's impossible for anyone else to define you. You are the only one who gets to say who you are. Cheer up!",
            "You were born with everything you need, you're not missing anything. Cheer up!",
            "Perfection is a man-made illusion. We are all beautifully imperfect. Cheer up!",
            "You are NOT your thoughts, you are the calm awareness behind the noise in your mind. Cheer up!",
            "Your beliefs can be modified to lift you up, you are fully in control of your happiness. Cheer up!",
            "The past and the future don't exist anywhere except in your mind. Now is the only real moment. Don't be sad. Cheer up!",
            "Your calling in life is to fully express who you already are, unapologetically shine your light. Cheer up!",
            "Challenges are gifts for your growth and can unlock your full potential. Cheer up!",
            "Forgiveness is a gift you give yourself, to be free. Don't be sad. Cheer up!",
            "Don't let the darkness steal the beautiful person you have inside, cheer up!!!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_page_2);
        Log.d("tag", "onCreate of MoodsPage");

        //Create all typefaces
        helvatica = Typeface.createFromAsset(getAssets(), "HELR45W.ttf");
        groBold = Typeface.createFromAsset(getAssets(), "GROBOLD.ttf");
        montserrat = Typeface.createFromAsset(getAssets(), "Montserrat-Regular.ttf");
        montserrat_bold = Typeface.createFromAsset(getAssets(), "Montserrat-Bold.ttf");
        vegur_light = Typeface.createFromAsset(getAssets(), "Vegur-Light.otf");
        vegur_bold = Typeface.createFromAsset(getAssets(), "Vegur-Bold.otf");
        vegur_regular = Typeface.createFromAsset(getAssets(), "Vegur-Regular.otf");

        // Reference to text inside all the card
        happyTextView = (TextView) findViewById(R.id.happyTextViewId);
        anxiousTextView = (TextView) findViewById(R.id.anxiousTextViewId);
        angryTextView = (TextView) findViewById(R.id.angryTextViewId);
        demotivatedTextView = (TextView) findViewById(R.id.demotivatedTextViewId);
        worthlessTextView = (TextView) findViewById(R.id.worthlessTextViewId);
        sadTextView = (TextView) findViewById(R.id.sadTextViewId);
        howAreYouQuestion = (TextView) findViewById(R.id.howAreYouQuestionTVId);


        // Set typeface for text inside card
        happyTextView.setTypeface(vegur_bold);
        anxiousTextView.setTypeface(vegur_bold);
        angryTextView.setTypeface(vegur_bold);
        demotivatedTextView.setTypeface(vegur_bold);
        worthlessTextView.setTypeface(vegur_bold);
        sadTextView.setTypeface(vegur_bold);
        howAreYouQuestion.setTypeface(vegur_bold);


    }

    public void myCardClicked(View v) {
        Log.d("tag", "Card was clicked");
        String moodName = "";
        String moodConsolingMessage = "";

        Random random = new Random();
        int messageIndex = random.nextInt(10);
        Log.d("tag", "Random number generated : " + messageIndex);

        if( (messageIndex > 9) || (messageIndex < 0) ) {
            messageIndex = 5;
        }
        Log.d("tag", "Random number generated : " + messageIndex);

        switch (v.getId()) {
            case R.id.happyCard:
                Log.d("tag", "Happy card was clicked");
                moodName = "Happy";
                moodConsolingMessage = happyMessages[messageIndex];
                break;
            case R.id.anxiousCard:
                Log.d("tag", "Anxious card was clicked");
                moodName = "Anxious";
                moodConsolingMessage = angryMessages[messageIndex];
                break;
            case R.id.angryCard:
                Log.d("tag", "Angry card was clicked");
                moodName = "Angry";
                moodConsolingMessage = angryMessages[messageIndex];
                break;
            case R.id.demotivatedCard:
                Log.d("tag", "Demotivated card was clicked");
                moodName = "Demotivated";
                moodConsolingMessage = demotivatedMessages[messageIndex];
                break;
            case R.id.worthlessCard:
                Log.d("tag", "Worthless card was clicked");
                moodName = "Worthless";
                moodConsolingMessage = worthlessMessages[messageIndex];
                break;
            case R.id.sadCard:
                Log.d("tag", "Sad card was clicked");
                moodName = "Sad";
                moodConsolingMessage = sadMessages[messageIndex];
                break;
                default:
                    Log.d("tag", "ID did not match any card id");
                    break;
        }

        Intent i = new Intent(MoodsPage.this, DescribeMoodPage.class);
        i.putExtra("MOOD_STATUS", moodName);
        i.putExtra("MOOD_CONSOLING_MESSAGE", moodConsolingMessage);
        startActivity(i);

    }
}
