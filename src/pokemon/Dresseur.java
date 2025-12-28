/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Flori
 */
public class Dresseur
{
    String nom;
    int age;
    String sexe;
    double taille;
    boolean sec;
    int [][] lst_obj = new int [3][3]; // { [2,3,0],[1,0,6],[1,2,3]} 
                            // [2,3,0] -> 2 potion de soins, 3 super soins, 0 hyper soin
                            // [1,0,6] -> 1 potion sleep, 0 potion poison, 6 potion paralyse
                            // [1,2,3] -> 1 pokeball, 2 super ball, 3 hyper balls
    
    Pokemon [] lst_pokemon = new Pokemon[6];
    
    
    
    
    public Dresseur(String nom, int age, String sexe, double taille){
        
        this.nom=nom;
        this.age= age;
        this.sexe = sexe;
        this.taille=taille;
        sec = false;
        
    }
    
    
    
    /** 
     * Cette fonction test si tous les pokemon d'un dresseur son KO
     */
    public boolean is_over(){
        
        boolean var = true;
        for (int i=0;i<lst_pokemon.length;i++) {
            if (lst_pokemon[i]!=null && lst_pokemon[i].ko==false) {
                var = false;
                break;
            }
        }
        return var;
    }
    
    
    
    /**
     * Cette fonction effectue tous les effets que possede une compétence
     */
    public void new_action_competence(int a, Dresseur ad){
        // 0<=a<=3
        for (Effet e : lst_pokemon[0].lst_competence[a].lst_effets){
            e.activate(this, ad, lst_pokemon[0].lst_competence[a].value,lst_pokemon[0].lst_competence[a].t);
        }
        
    }
    
    
    /**
     *Cette fonction effectue l'action d'un objet sur un pokemon
     * ( on repère l'objet en fonction d'une ligne et d'une colonne dans la matrice lst_objet du dresseur) 
     */
    public String new_action_objet(Dresseur d, int l, int c, int num_poke){
        
        if (l==0){
            switch (c){
                case 0:
                    d.rempot(1);
                    if (d.lst_pokemon[num_poke].hp+20>d.lst_pokemon[num_poke].hpmax){
                        d.lst_pokemon[num_poke].hp=d.lst_pokemon[num_poke].hpmax;
                    }
                    else {
                        d.lst_pokemon[num_poke].hp+=20;
                    }
                    return " une potion sur "+d.lst_pokemon[num_poke].name;
                case 1:
                    d.remsuperpot(1);
                    if (d.lst_pokemon[num_poke].hp+40>d.lst_pokemon[num_poke].hpmax){
                        d.lst_pokemon[num_poke].hp=d.lst_pokemon[num_poke].hpmax;
                    }
                    else {
                        d.lst_pokemon[num_poke].hp+=40;
                    }
                    return " une super potion sur "+d.lst_pokemon[num_poke].name;
                case 2:
                    d.remhyperpot(1);
                    if (d.lst_pokemon[num_poke].hp+100>d.lst_pokemon[num_poke].hpmax){
                        d.lst_pokemon[num_poke].hp=d.lst_pokemon[num_poke].hpmax;
                    }
                    else {
                        d.lst_pokemon[num_poke].hp+=100;
                    }
                    return " une hyper potion sur "+d.lst_pokemon[num_poke].name;
            }
        
        }
        
        if (l==1){
            switch (c){
                case 0:
                    d.rempotreveil(1);
                    d.lst_pokemon[num_poke].sleeping=false;
                    return " un réveil sur "+d.lst_pokemon[num_poke].name;
                    
                case 1:
                    d.rempotpois(1);
                    d.lst_pokemon[num_poke].poisoned=false;
                    return " un antidote sur "+d.lst_pokemon[num_poke].name;
                     
                case 2:
                    d.rempotpara(c);
                    d.lst_pokemon[num_poke].paralysed=false;
                    return " un anti-paralyse sur "+d.lst_pokemon[num_poke].name;
                    
            }
            
        }
        
        return"";
    }
    
    
    
    /**
     *Cette fonction vérifie si le pokemon en combat actuellement ( donc le pokemon d'indice 0 dans la liste de pokemon du dresseur )
     *est le seul encore en vie (non ko) 
     */
    public boolean last_survival(){
        
        boolean var = true;
        
        for (int i =0; i< this.lst_pokemon.length ;i++ ){
            
            if (this.lst_pokemon[i]!=null && this.lst_pokemon[i].ko==false){
                var = false;
            }
        }
        return var;
    }
   
    
    /**
     * Cette fonction reinitialise l'etat de tous les pokemon d'un dresseur
     */
    public void reset_all(){
        
        
        for ( Pokemon p : lst_pokemon){
            
            p.hp=p.hpmax;
            p.poisoned=false;
            p.sleeping=false;
            p.ko=false;
            p.paralysed=false;
            p.sec=false;
            
            
        }
        
        
    }
    
    
    
    
    
    // Commande pour potion de soins
    
    public void addpot(int i){
        lst_obj[0][0]+=i;
    }
    public void addsuperpot(int i){
        lst_obj[0][1]+=i;
    }
    public void addhyperpot(int i){
        lst_obj[0][2]+=i;
    }
    public void rempot(int i){
        lst_obj[0][0]-=i;
    }
    public void remsuperpot(int i){
        lst_obj[0][1]-=i;
    }
    public void remhyperpot(int i){
        lst_obj[0][2]-=i;
    }    

    // Commande pour potion de status    
    
    public void addpotreveil(int i){
        lst_obj[1][0]+=i;
    }
    public void addpotpois(int i){
        lst_obj[1][1]+=i;
    }
    public void addpotpara(int i){
        lst_obj[1][2]+=i;
    }
    public void rempotreveil(int i){
        lst_obj[1][0]-=i;
    }
    public void rempotpois(int i){
        lst_obj[1][1]-=i;
    }
    public void rempotpara(int i){
        lst_obj[1][2]-=i;
    }    
    
    // Commande pour Pokeball
    
    public void addpokeball(int i){
        lst_obj[2][0]+=i;
    }
    public void addsuperball(int i){
        lst_obj[2][1]+=i;
    }
    public void addhyperball(int i){
        lst_obj[2][2]+=i;
    }
    public void rempokeball(int i){
        lst_obj[2][0]-=i;
    }
    public void remsuperballs(int i){
        lst_obj[2][1]-=i;
    }
    public void remhyperball(int i){
        lst_obj[2][2]-=i;
    }    
    
}
















