package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;


public class RestaurantList {
    private ArrayList<Restaurant> restaurants;

    private Image imageBK=new Image("sample/data/Images/Restaurants_Picture/burger-king.jpg");
    private Image imageDragonDor=new Image("sample/data/Images/Restaurants_Picture/DragonDor.jpg");
    private Image imageFlunch=new Image("sample/data/Images/Restaurants_Picture/Flunch.png");
    private Image imageHippopotamus=new Image("sample/data/Images/Restaurants_Picture/Hippopotamus.jpg");
    private Image imageLaBoucherie=new Image("sample/data/Images/Restaurants_Picture/LaBoucherie.png");
    private Image imageMacdo=new Image("sample/data/Images/Restaurants_Picture/Macdo.jpg");
    private Image imageQuick=new Image("sample/data/Images/Restaurants_Picture/Quick.png");
    private Image imageSushiSpirit=new Image("sample/data/Images/Restaurants_Picture/SushiSpirit.png");

    private Image imageLidL=new Image("sample/data/images/Commerce_Picture/lidl.jpg");
    private Image imageBiocoop=new Image("sample/data/images/Commerce_Picture/biocoop.png");
    private Image imageCarrefour=new Image("sample/data/images/Commerce_Picture/Carrefour.jpeg");
    private Image imageCasino=new Image("sample/data/images/Commerce_Picture/Casino.png");
    private Image imageIntermache=new Image("sample/data/images/Commerce_Picture/Intermarche.png");
    private Image imageLeclerc=new Image("sample/data/images/Commerce_Picture/Leclerc.jpg");
    private Image imageNatureo=new Image("sample/data/images/Commerce_Picture/natureo.jpg");
    private Image imageOsushi=new Image("sample/data/images/Commerce_Picture/Osushi.jpg");

    public RestaurantList(){
        this.restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }

    public ArrayList<Restaurant> getRestaurants(){
        return this.restaurants;
    }
    public void printRestaurants(){
        for(int i=0;i<this.restaurants.size();i++){
            System.out.println("Information du restaurant numéro "+i+ ": ");
            this.restaurants.get(i).printRestaurant();
        }
    }

    public void sampleRestaurant(){
        String[] schedule = {"09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h"};
        Restaurant burgerKing = new Restaurant("BurgerKing", true,"36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,3.2,20,20, new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageBK);
        Restaurant chezBernard = new Restaurant("Chez Bernard", true,"36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,4.4,50,10,new ArrayList<Tag>(Arrays.asList(Tag.Francais, Tag.Fromage, Tag.Healthy)),imageLidL);
        Restaurant kebab = new Restaurant("Kebabinou", true,"36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,2.2,5,2, new ArrayList<Tag>(Arrays.asList(Tag.FastFood, Tag.Kebab)),imageLidL);
        Restaurant lidl = new Restaurant("LidL", false, "38 avenue de lidL, 06600 Antibes", "www.lidL.com", "0682392412", schedule, 4.2,3,5, new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageLidL);



        Restaurant resto0 = new Restaurant("NaturéO",false,"11 Avenue Jean Sébastien Bach, 06600 Antibes", "www.siteweb.com","042268552099",schedule,0.2,99,60,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageNatureo);
        Restaurant resto1 = new Restaurant("Carrefour",false,"26 Boulevard General de Gaulle, 06560 Valbonnes", "www.siteweb.com","042256243779",schedule,2.1,31,53,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageCarrefour);
        Restaurant resto2 = new Restaurant("Intermarché",false,"129 Avenue St Barthélemy, 06600 Antibes", "www.siteweb.com","042266547861",schedule,0.6,25,16,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageIntermache);
        Restaurant resto3 = new Restaurant("MacDonald",true,"164 Boulevard Frederick Mistral, 06410 Biot", "www.siteweb.com","042219065546",schedule,1.3,90,14,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageMacdo);
        Restaurant resto4 = new Restaurant("Flunch",true,"66 Avenue St Barthélemy, 06000 Nice", "www.siteweb.com","042234911853",schedule,4.6,56,11,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Glace)),imageFlunch);
        Restaurant resto5 = new Restaurant("Quick",true,"73 Avenue Frederick Mistral, 06000 Nice", "www.siteweb.com","042293089635",schedule,2.6,65,70,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Francais)),imageQuick);
        Restaurant resto6 = new Restaurant("Casion",false,"48 Avenue Jean Sébastien Bach, 06600 Antibes", "www.siteweb.com","042287955030",schedule,4.8,30,25,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Fromage)),imageCasino);
        Restaurant resto7 = new Restaurant("Leclerc",false,"151 Boulevard General de Gaulle, 06560 Valbonnes", "www.siteweb.com","042294949032",schedule,1.4,7,65,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Fromage)),imageLeclerc);
        Restaurant resto8 = new Restaurant("Leclerc",false,"1 Avenue Jean Sébastien Bach, 06600 Antibes", "www.siteweb.com","042227966621",schedule,0.4,62,58,new ArrayList<Tag>(Arrays.asList(Tag.Fromage,Tag.Crepe)),imageLeclerc);
        Restaurant resto9 = new Restaurant("Intermarché",false,"152 Boulevard Jean Sébastien Bach, 06150 Cannes", "www.siteweb.com","042217815796",schedule,0.6,45,5,new ArrayList<Tag>(Arrays.asList(Tag.Chinois,Tag.Japonais)),imageIntermache);
        Restaurant resto10 = new Restaurant("LiDL",false,"141 Boulevard Jean Sébastien Bach, 06150 Cannes", "www.siteweb.com","042293369692",schedule,2.1,53,74,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Salade)),imageLidL);
        Restaurant resto11 = new Restaurant("Sushi Spirit",true,"168 Boulevard Frederick Mistral, 06560 Valbonnes", "www.siteweb.com","042246716108",schedule,3.0,31,64,new ArrayList<Tag>(Arrays.asList(Tag.Salade, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageSushiSpirit);
        Restaurant resto12 = new Restaurant("Flunch",true,"153 Boulevard St Barthélemy, 06000 Nice", "www.siteweb.com","042212335161",schedule,3.8,26,1,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageFlunch);
        Restaurant resto13 = new Restaurant("Hippopotamus",true,"31 Avenue St Barthélemy, 06150 Cannes", "www.siteweb.com","042224059671",schedule,1.3,51,5,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Burger, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto14 = new Restaurant("Burger King",true,"97 Avenue St Barthélemy, 06560 Valbonnes", "www.siteweb.com","042255893848",schedule,0.7,84,66,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Kebab, Tag.Salade, Tag.Healthy)),imageBK);
        Restaurant resto15 = new Restaurant("LiDL",false,"23 Boulevard St Barthélemy, 06410 Biot", "www.siteweb.com","042267953103",schedule,4.7,70,45,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageLidL);
        Restaurant resto16 = new Restaurant("Dragon D’or",true,"39 Boulevard Jean Sébastien Bach, 06150 Cannes", "www.siteweb.com","042292456727",schedule,1.4,98,48,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Chinois, Tag.Salade, Tag.Healthy)),imageDragonDor);
        Restaurant resto17 = new Restaurant("Hippopotamus",true,"12 Avenue General de Gaulle, 06150 Cannes", "www.siteweb.com","042287069679",schedule,3.0,94,86,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto18 = new Restaurant("La Boucherie",true,"66 Avenue Frederick Mistral, 06000 Nice", "www.siteweb.com","042243284353",schedule,4.4,68,74,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Japonais, Tag.Salade, Tag.Healthy)),imageLaBoucherie);
        Restaurant resto19 = new Restaurant("Flunch",true,"31 Avenue Frederick Mistral, 06560 Valbonnes", "www.siteweb.com","042293044462",schedule,1.6,97,42,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Japonais, Tag.Fromage, Tag.Healthy)),imageFlunch);
        Restaurant resto20 = new Restaurant("O’Shushi",false,"140 Avenue Albert Cacot, 06600 Antibes", "www.siteweb.com","042209472586",schedule,2.7,65,57,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageOsushi);
        Restaurant resto21 = new Restaurant("Quick",true,"93 Boulevard Frederick Mistral, 06000 Nice", "www.siteweb.com","042299326821",schedule,1.9,92,53,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Italien, Tag.Salade, Tag.Healthy)),imageQuick);
        Restaurant resto22 = new Restaurant("Burger King",true,"154 Boulevard Albert Cacot, 06000 Nice", "www.siteweb.com","042290638866",schedule,1.2,29,19,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Pizza, Tag.Italien, Tag.Healthy)),imageBK);
        Restaurant resto23 = new Restaurant("La Boucherie",true,"105 Boulevard Jean Sébastien Bach, 06560 Valbonnes", "www.siteweb.com","042220777930",schedule,0.9,86,84,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sushi, Tag.Japonais, Tag.Healthy)),imageBiocoop);
        Restaurant resto24 = new Restaurant("Biocoop",false,"11 Boulevard Albert Cacot, 06560 Valbonnes", "www.siteweb.com","042283681414",schedule,2.5,9,56,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageHippopotamus);
        Restaurant resto25 = new Restaurant("Hippopotamus",true,"109 Boulevard General de Gaulle, 06000 Nice", "www.siteweb.com","042218803297",schedule,4.5,91,61,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto26 = new Restaurant("Hippopotamus",true,"31 Avenue St Barthélemy, 06600 Antibes", "www.siteweb.com","042255595547",schedule,2.9,95,52,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto27 = new Restaurant("Dragon D’or",true,"18 Boulevard St Barthélemy, 06560 Valbonnes", "www.siteweb.com","042202224705",schedule,2.5,44,30,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageDragonDor);
        Restaurant resto28 = new Restaurant("Hippopotamus",true,"97 Boulevard Frederick Mistral, 06600 Antibes", "www.siteweb.com","042222970757",schedule,4.8,77,18,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto29 = new Restaurant("O’Shushi",false,"46 Boulevard Frederick Mistral, 06150 Cannes", "www.siteweb.com","042236841707",schedule,4.8,16,56,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Salade, Tag.Japonais)),imageOsushi);
        Restaurant resto30 = new Restaurant("NaturéO",false,"97 Boulevard General de Gaulle, 06600 Antibes", "www.siteweb.com","042243993088",schedule,0.4,35,52,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageNatureo);
        Restaurant resto31 = new Restaurant("Quick",true,"138 Boulevard Albert Cacot, 06600 Antibes", "www.siteweb.com","042230605667",schedule,0.4,36,79,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageQuick);
        Restaurant resto32 = new Restaurant("NaturéO",false,"26 Boulevard General de Gaulle, 06000 Nice", "www.siteweb.com","042226072265",schedule,2.1,68,79,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Italien)),imageNatureo);
        Restaurant resto33 = new Restaurant("Hippopotamus",true,"121 Boulevard General de Gaulle, 06560 Valbonnes", "www.siteweb.com","042239429721",schedule,2.0,28,23,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageHippopotamus);
        Restaurant resto34 = new Restaurant("Flunch",true,"85 Avenue General de Gaulle, 06560 Valbonnes", "www.siteweb.com","042286819541",schedule,4.4,0,77,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageFlunch);
        Restaurant resto35 = new Restaurant("O’Shushi",false,"150 Boulevard Jean Sébastien Bach, 06150 Cannes", "www.siteweb.com","042242638301",schedule,2.0,65,15,new ArrayList<Tag>(Arrays.asList(Tag.Pizza,Tag.FastFood)),imageOsushi);
        Restaurant resto36 = new Restaurant("Intermarché",false,"105 Avenue Jean Sébastien Bach, 06000 Nice", "www.siteweb.com","042280086990",schedule,2.4,37,50,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageIntermache);
        Restaurant resto37 = new Restaurant("LiDL",false,"114 Boulevard St Barthélemy, 06150 Cannes", "www.siteweb.com","042291998592",schedule,4.1,39,87,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageLidL);
        Restaurant resto38 = new Restaurant("Quick",true,"323 Avenue General de Gaulle, 06600 Antibes", "www.siteweb.com","042285386279",schedule,3.0,77,10,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Fromage, Tag.Salade, Tag.Healthy)),imageQuick);
        Restaurant resto39 = new Restaurant("Intermarché",false,"98 Boulevard Albert Cacot, 06000 Nice", "www.siteweb.com","042250356005",schedule,0.1,5,76,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageIntermache);
        Restaurant resto40 = new Restaurant("La Boucherie",true,"171 Avenue Jean Sébastien Bach, 06560 Valbonnes", "www.siteweb.com","042260123097",schedule,1.1,20,43,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Japonais, Tag.Healthy)),imageLaBoucherie);
        Restaurant resto41 = new Restaurant("Quick",true,"179 Avenue Albert Cacot, 06410 Biot", "www.siteweb.com","042267783161",schedule,0.1,62,74,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageQuick);
        Restaurant resto42 = new Restaurant("Flunch",true,"49 Avenue Albert Cacot, 06150 Cannes", "www.siteweb.com","042231146218",schedule,3.8,59,88,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Salade, Tag.Healthy)),imageFlunch);
        Restaurant resto43 = new Restaurant("O’Shushi",false,"54 Boulevard Albert Cacot, 06410 Biot", "www.siteweb.com","042243155501",schedule,3.9,34,60,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageOsushi);
        Restaurant resto44 = new Restaurant("MacDonald",true,"94 Avenue Jean Sébastien Bach, 06600 Antibes", "www.siteweb.com","042247326593",schedule,4.3,57,81,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Japonais, Tag.Italien, Tag.Chinois)),imageMacdo);
        Restaurant resto45 = new Restaurant("NaturéO",false,"102 Boulevard General de Gaulle, 06150 Cannes", "www.siteweb.com","042287641623",schedule,3.1,31,87,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageNatureo);
        Restaurant resto46 = new Restaurant("Casion",false,"99 Boulevard St Barthélemy, 06150 Cannes", "www.siteweb.com","042231085103",schedule,1.7,83,71,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageCasino);
        Restaurant resto47 = new Restaurant("La Boucherie",true,"102 Boulevard General de Gaulle, 06150 Cannes", "www.siteweb.com","042268493561",schedule,2.3,9,53,new ArrayList<Tag>(Arrays.asList(Tag.Commerce, Tag.Sandwich, Tag.Pizza, Tag.Japonais)),imageLaBoucherie);
        Restaurant resto48 = new Restaurant("Casion",false,"108 Boulevard Frederick Mistral, 06000 Nice", "www.siteweb.com","042298515224",schedule,0.4,50,50,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.Kebab)),imageCasino);
        Restaurant resto49 = new Restaurant("Leclerc",false,"36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes", "www.siteweb.com","042205072075",schedule,2.2,37,8,new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),imageLeclerc);


        Restaurant[] listOfRestaurants = {burgerKing,chezBernard,kebab,lidl,resto0,resto1,resto2,resto3,resto4,resto5,resto6,resto7,resto8,resto9,resto10,resto11,resto12,resto13,resto14,resto15,resto16,resto17,resto18,resto19,resto20,resto21,resto22,resto23,resto24,resto25,resto26,resto27,resto28,resto29,resto30,resto31,resto32,resto33,resto34,resto35,resto36,resto37,resto38,resto39,resto40,resto41,resto42,resto43,resto44,resto45,resto46,resto47,resto48,resto49};
        burgerKing.getCommentList().addComment(new Comment("Tres bon service avec de tres bon burger",burgerKing));
        burgerKing.getCommentList().addComment(new Comment("Un fastfood classique, sans plus",burgerKing));

        chezBernard.getCommentList().addComment(new Comment("Accueil chaleureux et on est bien nourri !",chezBernard));
        chezBernard.getCommentList().addComment(new Comment("Un restaurant miteux",chezBernard));

        kebab.getCommentList().addComment(new Comment("Un kebab moyen mais pas cher",kebab));
        kebab.getCommentList().addComment(new Comment("Un tres bon kebab, je recommande !",kebab));

        lidl.getCommentList().addComment(new Comment("La qualité est a la hauteur de ces prix...",lidl));
        lidl.getCommentList().addComment(new Comment("Pas cher et pas bon, génial !",lidl));
        lidl.getCommentList().addComment(new Comment("Un endroit ou manger quand on est etudiant",lidl));
        this.restaurants.addAll(Arrays.asList(listOfRestaurants));

    }

    public int size() {
        return this.restaurants.size();
    }

    public Restaurant getRestaurant(int i) {
        return this.restaurants.get(i);
    }
}
