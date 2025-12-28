/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;

/**
 *
 * @author Flori
 */
public class Type
{
    String nom;
    Type [] lst_supp = new Type [5];   // lst des type contre lequel celui-ci est efficace
    Type [] lst_inf = new Type [5]; // lst des type contre lequel celui-ci est inefficace
    
    
    public Type(String nom){
        
        this.nom=nom;
        
        
    }
    
    
    /**
     * Cette fonction  renvoie la valeur du coefficient d'efficacité de type contre un autre type
     * 
     * si le Type appelant cette fonction est suppérieur au type recu en argument, alors le coefficient sera de 2
     * si au contraire cest le type en argument qui est superieur, le coefficient sera de 0.5
     * si aucun n'est superieur le coefficient sera de 1
     * 
     * le coefficient sert a regler les dégat d'une attaque sur un pokemon.
     * Par exemple, Plante > Eau
     *              Eau > Feu
     *              Feu > Plante
     * 
     */
    public double efficace(Type t){ // cette fonction renvoie la valeur du coeff en fonction des types
        
        double eff = 1.;
        
        for (Type val : lst_supp ){
            if (val!=null && val.nom==t.nom){
                eff = 2.;
                break;  
            }
        }
        if (eff == 1){
            for (Type val : lst_inf){
                if (val!=null && val.nom==t.nom){
                    eff= 0.5;
                    break;
                }
            }
        }
        return eff;
        
    }
    
    
    
    
    /**
     * Cette fonction ajoute le type en argument au type appelant cette methode dans sa liste lst_supp
     */
    public void superieur(Type [] lst){
        
        for (int i=0; i<lst.length;i++){
            lst_supp[i]=lst[i];
        }
    }
    
     /**
     * Cette fonction ajoute le type en argument au type appelant cette methode dans sa liste lst_inf
     */
    public void inferieur(Type [] lst){
        
        for (int i=0; i<lst.length;i++){
            lst_inf[i]=lst[i];
        }
    }
    
    
}
