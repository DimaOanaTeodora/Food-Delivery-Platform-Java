package com.company.Product;

import java.util.Scanner;

public class Drink extends Product{
        private String flavour;

        public Drink(){}
        public Drink(String name, double price, String measurement, int quantity, String flavour) {
                super(name, price, measurement, quantity);
                this.flavour = flavour;
        }
        @Override
        public void reader(){
                Scanner var=new Scanner (System.in);

                System.out.print("Drink's name:");
                String name=var.nextLine();


                System.out.print("Drink's measurement:");
                String measurement=var.nextLine();


                System.out.print("Drink's falvour:");
                String flavour=var.nextLine();

                System.out.print("Drink's quantity:");
                int quantity=var.nextInt();

                System.out.print("Drink's price:");
                Double price=var.nextDouble();

                this.name=name;
                this.price=price;
                this.measurement=measurement;
                this.quantity=quantity;
                this.flavour=flavour;

        }

        @Override
        public String toString() {
                return "Drink{" +
                        "flavour='" + flavour + '\'' +
                        ", name='" + name + '\'' +
                        ", price=" + price +
                        ", measurement='" + measurement + '\'' +
                        ", quantity=" + quantity +
                        '}';
        }
}
