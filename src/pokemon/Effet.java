/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon;

/**
 *
 * @author Flori
 */
public class Effet
{
    
    String but;
    
    public Effet( String but){
        
        this.but=but;
    }
    
    /**
     * Cette fonction effectue l'effet en question. si L'effet est "damage" cela fera des dégats
     *                                              si L'effet est "poison" le pokemon ciblé sera endormi
     *                                              ect...
     */
   public void activate(Dresseur d1,Dresseur d2, double val,Type t){
       
       if (but=="damage"){
           double dmg = val*t.efficace(d2.lst_pokemon[0].type1)*(1-d2.lst_pokemon[0].defense/400);
           
           if (d2.lst_pokemon[0].hp-dmg <=0){
               d2.lst_pokemon[0].hp=0;
               d2.lst_pokemon[0].ko=true;
               
           }
           else {
               d2.lst_pokemon[0].hp-=dmg;
           }
           
       }
       
       
       if (but=="soin"){
           d1.lst_pokemon[0].hp +=val/2;}

       if (but=="sleep"){
           d2.lst_pokemon[0].sleeping =true;}
       
       if (but=="poison"){
           d2.lst_pokemon[0].poisoned =true;}

       if (but=="paralysie"){
           d2.lst_pokemon[0].paralysed =true;}
       
       if (but=="brulure"){
           d2.lst_pokemon[0].burned =true;}
       
       
       
       
       
       
       // LE RESTE N'A PAS ETE IMPLEMENTE
       
        if (but=="incr_def"){
           d1.lst_pokemon[0].defense+=val;}
       
       if (but=="incr_spd"){
           d1.lst_pokemon[0].speed +=val;}

       if (but=="incr_atk"){
           d1.lst_pokemon[0].attack +=val;}
       
       if (but=="confusion"){    
       }       
       
       if (but=="peur"){    
       }
       
   }
    
    
}
