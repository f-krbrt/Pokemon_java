/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;

/**
 *
 * @author Flori
 */
public class Competence
{
    String nom;
    Type t;
    double value;
    Effet [] lst_effets;
    
    
    public Competence(String nom, Type t, Effet [] lst,double value){
        this.nom = nom;
        this.t=t;
        lst_effets =lst;
        this.value=value;
        
    }
    
    
    
    
}
