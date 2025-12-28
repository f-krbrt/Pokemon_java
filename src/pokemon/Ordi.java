package pokemon;
import java.util.ArrayList;
import java.util.Random;
        
/**
 *
 * @author anask
 */
public class Ordi {
    Random rd = new Random();
    
    Fenetre_Combat fen;
    
    
    
    
    public Ordi(Fenetre_Combat fen){
        this.fen=fen;
    }
    
    /**
     * Cette Fonction determine le prochain choix de l'ordi parmis attaquer ( si oui quel attaque )
     *                                                  et utiliser un objet ( si oui lequel et sur quel pokemon ?)
     */
    public String[] Heuristique_aleatoire2(Dresseur d2, Dresseur d1){
        
        
        //CETTE FONCTION RENVOIE UN TABLEAU DE STRING DE LONGEUR 4 
        //[OBJET/ATTAQUE/SWITCH/ ; ChoixObjet/choix Attaque/Choix_Pokemon ; appliqué a quel pokemon l'objet ? )
        // choix objet est défini par 2 entier voir class Dresseur
        
        
        

        
        String [] tab = new String [4];
        ArrayList<String> echantillon = new ArrayList<String>();
        int i =0;
        
        for (int j=1;i<d2.lst_pokemon.length;i++){
            if (d2.lst_pokemon[i]!=null && d2.lst_pokemon[i].ko==false){
                echantillon.add(""+j);
            }
        }
        
        
        
        
        if ((isNull(d2.lst_obj)!=true)&&(!"".equals(echantillon.get(0)))){
            
            
            //int nbr_aleatoire = rd.nextInt(3);
            int nbr_aleatoire = rd.nextInt(3);
            if (nbr_aleatoire == 0 || nbr_aleatoire == 1){
                int idAttaque = rd.nextInt(4);
                tab[0] = "attaque";
                tab[1] = ""+idAttaque;
                return tab;
            }
            
            /*
            //Ajouter un garde fou (aucun autre pokemon en vie
            else if (nbr_aleatoire == 1 || d2.last_survival() ){
                int idPoke = rd.nextInt(echantillon.size());
                tab[0]="switch";
                tab[1]=echantillon.get(idPoke);
                return tab;
                        
            }
*/
            else if (nbr_aleatoire == 2){
                int id1Object = rd.nextInt(d2.lst_obj.length);
                int id2Object = rd.nextInt(d2.lst_obj[id1Object].length);
                int idPoke = rd.nextInt(echantillon.size());
                tab[0]="objet";
                tab[1]=""+id1Object;
                tab[2]=""+id2Object;
                tab[3]=""+idPoke;
                return tab;
                
                
            }
        }
            

        else if ((isNull(d2.lst_obj)==true)&&(!"".equals(echantillon.get(0)))){
            
            int nbr_aleatoire = 0; // rd.nextInt(2);
            if (nbr_aleatoire == 0 || d2.last_survival() ){
                int idAttaque = rd.nextInt(4);
                tab[0] = "attaque";
                tab[1] = ""+idAttaque;
                return tab;
            }
            /*
            //Ajouter un garde fou (aucun autre pokemon en vie
            else if (nbr_aleatoire == 1){
                int idPoke = rd.nextInt(echantillon.size());
                tab[0]="switch";
                tab[1]=echantillon.get(idPoke);
                return tab;                        
            }*/
        }
        
        else if ((isNull(d2.lst_obj)!=true)&&("".equals(echantillon.get(0)))){
            
            int nbr_aleatoire = rd.nextInt(2);
            if (nbr_aleatoire == 0){
                int id1Object = rd.nextInt(d2.lst_obj.length);
                int id2Object = rd.nextInt(d2.lst_obj[id1Object].length);
                int idPoke = rd.nextInt(echantillon.size());
                tab[0]="objet";
                tab[1]=""+id1Object;
                tab[2]=""+id2Object;
                tab[3]=""+idPoke;
                return tab;
                
            }
            else if (nbr_aleatoire == 1){
                int idAttaque = rd.nextInt(4);
                tab[0] = "attaque";
                tab[1] = ""+idAttaque;
                return tab;
            }          
        }
        
        else if ((isNull(d2.lst_obj)==true)&&("".equals(echantillon.get(0)))){
            
            
            int idAttaque = rd.nextInt(4);
                tab[0] = "attaque";
                tab[1] = ""+idAttaque;
                return tab;
        }
        
    
    String [] probleme = new String [2];
    return probleme;
    
    
    }

    /**
     * 
     * Cette fonction vérifie si une matrice est nulle 
     */
    public boolean isNull(int [][]Mat){
        for (int i=0;i<Mat.length;i++) {
            for (int j=0;j<Mat[i].length;j++){
                if (Mat[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
        
    
}
    
    /**
     * Cette fonction demande a l'ordinateur de choisir un nouveau pokemon lors que le sien devient KO
    */
    public void switch_by_ko_ordi(Dresseur d2, Dresseur d1, Fenetre_Combat fen){
        ArrayList<Integer> lst_poke_switch = new ArrayList<Integer>();
        int k=7; //k est l'indice du nouveau pokemon a entrer
        
        for (int i=1 ; i<d2.lst_pokemon.length ; i++){
            if (d2.lst_pokemon[i]!=null && d2.lst_pokemon[i].ko!=true){
                lst_poke_switch.add(i);
            }
        }
        
        
        for (Integer i=0;i<lst_poke_switch.size();i++){
        }
        
        for (int j=1 ; j<lst_poke_switch.size();j++){
            
            if ((d2.lst_pokemon[j].type1.efficace(d1.lst_pokemon[0].type1)==2.0) || (d2.lst_pokemon[j].type2.efficace(d1.lst_pokemon[0].type1)==2.0)
                || (d2.lst_pokemon[j].type1.efficace(d1.lst_pokemon[0].type2)==2.0) || (d2.lst_pokemon[j].type2.efficace(d1.lst_pokemon[0].type2)==2.0)){
                k=j;
                break;
            
            }
        }
        if (k==7){
            for (int l = 0; l<lst_poke_switch.size();l++){
                if ((d1.lst_pokemon[0].type1.efficace(d2.lst_pokemon[l].type1)==0.5) || (d1.lst_pokemon[0].type1.efficace(d2.lst_pokemon[l].type2)==0.5)
                    || (d1.lst_pokemon[0].type2.efficace(d2.lst_pokemon[l].type1)==0.5) || (d1.lst_pokemon[0].type2.efficace(d2.lst_pokemon[l].type2)==0.5)){
                    k=lst_poke_switch.get(l);
                    break;
                }
            }
        }
        
        if (k==7){
            k=lst_poke_switch.get(0);
        }
        
        
        fen.Switch(d2, ""+k);
        fen.ShowMain();
        
        
        
    }
}

