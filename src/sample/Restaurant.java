package sample;

import java.util.ArrayList;

public class Restaurant {
    private String restaurant, adress, website, phoneNumber;
    private String[] schedule;
    private ArrayList<Tag> tags;
    private double grade, price, distance;

    public Restaurant(String restaurant, String adress, String website, String phoneNumber, String[] schedule, double grade, double price, double distance, ArrayList<Tag> tags){
        this.restaurant=restaurant;
        this.adress=adress;
        this.website=website;
        this.phoneNumber=phoneNumber;
        this.schedule=schedule;
        this.grade=grade;
        this.price=price;
        this.distance=distance;
        this.tags = tags;
    }

    public double getGrade(){
        return this.grade;
    }

    public double getPrice(){
        return this.price;
    }

    public double getDistance(){
        return this.price;
    }

    public String getName(){
        return this.restaurant;
    }

    public ArrayList<Tag> getTags(){ return this.tags;}

    public void printRestaurant(){
        System.out.println("Le nom du restaurant est : "+this.restaurant);
        System.out.println("L'adresse du restaurant est : "+this.adress);
        System.out.println("Le site internet du restaurant est : "+this.website);
        System.out.println("Le numéro de téléphone du restaurant est : "+this.phoneNumber);
        System.out.println("L'emploi du temps du restaurant est le suivant : ");
        for(int i=0;i<schedule.length;i++){
            System.out.println("Jour "+i+" le restaurant a pour horaires "+schedule[i]);
        }
        System.out.println("Le restaurant a pour note : "+grade);
    }
}
