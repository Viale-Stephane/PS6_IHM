package com.example.georesto.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.georesto.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RestaurantList {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public RestaurantList() {
    }

    public RestaurantList(List<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public void sampleRestaurant(Context context) {
        String[] schedule1 = {"de 08:30 à 22:00", "de 08:30 à 22:00", "de 08:30 à 22:00", "de 08:30 à 22:00", "de 08:30 à 22:00", "de 08:30 à 22:00", "de 8h30 à 12h30"};
        String[] schedule2 = {"de 11:30 à 22:00", "de 11:30 à 22:00", "de 11:30 à 22:00", "de 11:30 à 22:00", "de 11:30 à 22:00", "de 11:30 à 22:00", "de 11:30 à 22:00"};
        String[] schedule3 = {"de 11:00 à 14:30", "de 11:00 à 14:30", "de 11:00 à 14:30", "de 11:00 à 14:30", "de 11:00 à 14:30", "de 11:00 à 14:30", ""};

        //ArrayList<Tag> tagsBK = (ArrayList<Tag>) Arrays.asList(Tag.Pizza,Tag.Burger);
        //tagsBK.add(Tag.FastFood);
        //tagsBK.add(Tag.Burger);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.camille);
        Restaurant burgerKing = new Restaurant("BurgerKing", true, "36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes", "http://www.bkvousecoute.fr", "0422000150", schedule2, 3.2, 15, new ArrayList<>(Arrays.asList(Tag.Burger, Tag.FastFood)), new LatLng(43.599070, 7.085523), bitmap);
        Restaurant mcDonaldS = new Restaurant("McDonald's", true, "1990 Route de Grasse, 06600 Antibes", "http://www.mcdonalds.fr", "0492910000", schedule2, 4.4, 50, new ArrayList<>(Arrays.asList(Tag.Burger, Tag.Fromage, Tag.FastFood)), new LatLng(43.599959, 7.086446), bitmap);
        Restaurant ilRistorante = new Restaurant("IL RISTORANTE", true, "50 voie Marie Fisher, Centre commercial Olympie, 06160 Antibes", "http://www.ilristorante.fr", "0422000150", schedule3, 3.2, 5, new ArrayList<>(Arrays.asList(Tag.Italien, Tag.Fromage)), new LatLng(43.599730, 7.085336), bitmap);
        Restaurant laCiteDOr = new Restaurant("La Cité d'or", true, "172 Avenue Weisweiller, 06600 Antibes", "http://restaurantlacitedor.com", "0422000150", schedule3, 1.2, 19, new ArrayList<>(Arrays.asList(Tag.Chinois, Tag.Viande, Tag.FastFood)), new LatLng(43.597924, 7.084902), bitmap);
        Restaurant carrefourAntibes = new Restaurant("Carrefour", false, "Chemin de Saint-Claude, 06600 Antibes", "http://www.carrefour.fr", "0682392412", schedule1, 4.2, 3, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.FastFood, Tag.Healthy)), new LatLng(43.603813, 7.089160), bitmap);
        Restaurant casinoSophia = new Restaurant("Casino", false, "St Philippe II, Avenue Roumanille, 06410 Biot", "http://www.magasins.supercasino.fr/supermarche/biot/CM898", "0497153030", schedule1, 3.9, 5, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.FastFood, Tag.Healthy,Tag.Fromage)), new LatLng(43.617340, 7.074475), BitmapFactory.decodeResource(context.getResources(), R.drawable.casinotem));
        Restaurant subwayGarbe = new Restaurant("Subway", true, "8 Place Méjane, 06550 Valbonne", "http://www.subwayfrance.fr", "0492901408", schedule2, 4.5, 13, new ArrayList<>(Arrays.asList(Tag.Sandwich,Tag.Salade,Tag.FastFood)), new LatLng(43.622410, 7.046509), BitmapFactory.decodeResource(context.getResources(), R.drawable.subwaygarbe));
        Restaurant subwayTemplier = new Restaurant("Subway", true, "200 Avenue Roumanille, 06410 Biot", "http://www.subwayfrance.fr", "0493653943", schedule2, 4.7, 13, new ArrayList<>(Arrays.asList(Tag.Sandwich,Tag.Salade,Tag.FastFood)), new LatLng(43.617365, 7.074361), BitmapFactory.decodeResource(context.getResources(), R.drawable.subwaytemp));
        Restaurant leclercAntibes = new Restaurant("Leclerc", false, "55 Avenue de Cannes, 06160 Antibes", "http://www.e-leclerc.com/antibes", "0493612222", schedule1, 4.3, 3, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.FastFood, Tag.Healthy,Tag.Fromage)), new LatLng(43.572543, 7.090654), BitmapFactory.decodeResource(context.getResources(), R.drawable.leclercant));
        Restaurant sushiSpiritA = new Restaurant("Sushi Spirit", true, "23 Boulevard du Président Wilson, 06600 Antibes", "http://www.sushispirit.com", "0492909010", schedule3, 2.7, 20, new ArrayList<>(Arrays.asList(Tag.Japonais,Tag.Sushi)), new LatLng(43.568605, 7.112873), BitmapFactory.decodeResource(context.getResources(), R.drawable.sushispirit));
        Restaurant biocoopNice1 = new Restaurant("Biocoop", false, "2 Place Grimaldi, 06000 Nice", "http://www.biocoop.fr", " 0493812260", schedule1, 4.6, 10, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.Healthy, Tag.Salade)), new LatLng(43.697822, 7.265354), BitmapFactory.decodeResource(context.getResources(), R.drawable.biocoop));
        Restaurant biocoopNice2 = new Restaurant("Biocoop", false, "2 Rue Cais de Pierlas, 06300 Nice", "http://www.biocoop.fr", " 0493812260", schedule1, 4.1, 10, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.Healthy, Tag.Salade)), new LatLng(43.702951, 7.281630), BitmapFactory.decodeResource(context.getResources(), R.drawable.biocoop));
        Restaurant intermarch = new Restaurant("Intermarché", false, "Quartier Du Plan, 06270 Roquefort-les-Pins", "http://www.intermarche.com", " 0493770334", schedule1, 3.1, 10, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.Healthy, Tag.Fromage)), new LatLng(43.669753, 7.047619), BitmapFactory.decodeResource(context.getResources(), R.drawable.interroquefort));
        Restaurant laBoucherie = new Restaurant("La Boucherie", true, "Parc de Sophia, 502 Rue Albert Caquot, 06560 Valbonne", "https://http://www.la-boucherie.fr", "0492384380", schedule3, 3.1, 15, new ArrayList<>(Arrays.asList(Tag.Viande, Tag.Francais, Tag.Burger, Tag.Fromage)), new LatLng(43.619850, 7.055750), BitmapFactory.decodeResource(context.getResources(), R.drawable.laboucherie));
        Restaurant boucherieLoufti = new Restaurant("Boucherie Chez Loufti", false, "1 Place de la Souste, 06560 Valbonne", "https://http://www.la-boucherie.fr", "0493654871", schedule3, 2.3, 10, new ArrayList<>(Arrays.asList(Tag.Viande, Tag.Francais, Tag.Fromage)), new LatLng(43.622530, 7.046760), BitmapFactory.decodeResource(context.getResources(), R.drawable.loufti));
        Restaurant kingsGrill = new Restaurant("Kings Grill", true, "18 Rue Fourmillière, 06600 Antibes", "http://www.kingsgrilljh.com/", "0606783289", schedule3, 1.7, 8, new ArrayList<>(Arrays.asList(Tag.Sandwich, Tag.Kebab, Tag.FastFood)), new LatLng(43.580170, 7.122880), BitmapFactory.decodeResource(context.getResources(), R.drawable.kingsgrill));
        Restaurant dejSoleil = new Restaurant("Déjeuner du Soleil", true, "1503 Route des Dolines, 06560 Valbonne", "http://http://www.dejeuner-desoleil.com", "0493958696", schedule3, 3.7, 8, new ArrayList<>(Arrays.asList(Tag.Crepe, Tag.Francais,Tag.Salade, Tag.Pizza, Tag.Italien, Tag.Glace)), new LatLng(43.616690, 7.039220), BitmapFactory.decodeResource(context.getResources(), R.drawable.dejsoleil));
        Restaurant flunch = new Restaurant("Flunch", true, "Chemin de Saint-Claude, 06600 Antibes", "http://restaurant.flunch.fr", "0493332399", schedule2, 3.1, 5, new ArrayList<>(Arrays.asList(Tag.Salade, Tag.Viande)), new LatLng(43.599340, 7.100710), BitmapFactory.decodeResource(context.getResources(), R.drawable.flucnh));
        Restaurant hongYun = new Restaurant("Hong Yun", true, "4 Avenue Tournelli, 06600 Antibes", "https://www.facebook.com/hongyun.fr/", "0493347804", schedule1, 4.2, 20, new ArrayList<>(Arrays.asList(Tag.Chinois)), new LatLng(43.583000, 7.120630), BitmapFactory.decodeResource(context.getResources(), R.drawable.hongyun));
        Restaurant artdoise = new Restaurant("L'Artdoise Craie L'Histoire", true, "200 Avenue Roumanille Espace Saint Philippe Sophia Antipolis 06410 Biot", "http://www.lartdoise-craie-lhistoire.fr", "0492901088", schedule3, 4.7, 7, new ArrayList<>(Arrays.asList(Tag.Kebab,Tag.Sandwich, Tag.Pizza)), new LatLng(43.618179, 7.074945), BitmapFactory.decodeResource(context.getResources(), R.drawable.artdoise));
        Restaurant kashmir = new Restaurant("Kashmir", true, "11 Rue de la Fontaine, 06560 Valbonne", "https://www.lekashmirvalbonne.fr", " 0493751615", schedule3, 4.4, 18, new ArrayList<>(Arrays.asList(Tag.Indien,Tag.Vege)), new LatLng(43.641689, 7.009170), BitmapFactory.decodeResource(context.getResources(), R.drawable.kashmir));


        Restaurant[] listOfRestaurants = {artdoise,hongYun,flunch,dejSoleil, kingsGrill, boucherieLoufti, laBoucherie, burgerKing, mcDonaldS, ilRistorante, laCiteDOr, carrefourAntibes,casinoSophia,subwayGarbe,subwayTemplier,leclercAntibes,sushiSpiritA,biocoopNice1,biocoopNice2,intermarch,kashmir};

        this.restaurants.addAll(Arrays.asList(listOfRestaurants));
    }

    public ArrayList<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public Restaurant getRestaurant(int i) { return this.restaurants.get(i); }

    public int size() {return this.restaurants.size(); }
}
