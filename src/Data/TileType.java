/**
 * Created by Gluayz on 31/10/2559.
 */
package Data;

public enum TileType {

    Grass("grass", true),Tower1("towns1", true),NULL("water64", false),Tower111("tw111", true),Tower112("tw112", true)
    ,Tower113("tw113", true),Tower114("tw114", true),Tower121("tw121", true),Tower122("tw122", true),Tower123("tw123", true)
    ,Tower124("tw124", true),Tower131("tw131", true),Tower132("tw132", true),Tower133("tw133", true),Tower134("tw134", true)
    ,EFF("btw2",true),MONEY("money", true);

    String textureName;
    boolean buildable;

    TileType(String textureName, boolean buildable){
        this.textureName = textureName;
        this.buildable = buildable;
    }
}
