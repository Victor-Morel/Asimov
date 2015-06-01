package model.graph;

/**
 * Created by victor on 01/06/15.
 */
public class StringLabel implements Label{

    String label;

    public StringLabel(String label) {
        super();
        this.label = label;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = (String) label;
    }

    public String toString(){
        return label ;
    }
}
