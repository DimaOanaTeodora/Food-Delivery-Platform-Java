package com.company.product;

import java.util.Scanner;

public class Drink extends Product{
        private String flavour;
        //in numele bauturii adaug si cantitatea


        public Drink(){}
        public Drink(String name, double price, String flavour) {
                super(name, price);
                this.flavour = flavour;
        }
        @Override
        public void reader(){
                Scanner var=new Scanner (System.in);

                System.out.print("Drink's name + quantity:");
                String name=var.nextLine();

                System.out.print("Drink's falvour:");
                String flavour=var.nextLine();

                System.out.print("Drink's price:");
                Double price=var.nextDouble();

                this.name=name;
                this.price=price;
                this.flavour=flavour;

        }

        @Override
        public String toString() {
                String output="----Product:Drink-----\n";
                output+="Name: "+this.name+"\n";
                output+="Flavour: "+this.flavour+"\n";
                output+="Price: "+this.price+"lei\n";

                return output;

        }
}
