/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.FetchProfile.Item;

/*import org.icefaces.samples.showcase.dataGenerators.ImageSet;
import org.icefaces.samples.showcase.dataGenerators.ImageSet.ImageInfo;
import org.icefaces.samples.showcase.example.ace.accordionpanel.Item;*/

@ManagedBean(name= TabSetBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class TabSetBean implements Serializable 
{
    public static final String BEAN_NAME = "tabSet";
    public String getBeanName() { return BEAN_NAME; }
    
    private List<Item> items;
    //private ImageSet.ImageInfo image;
    private LinkedHashMap <String, Integer> toDoList;

    public TabSetBean() {
        initializeInstanceVariables();
    }

    private void initializeInstanceVariables()
    {
        //items = populateListWithItems();
        toDoList = populateToDoList();
        //image = ImageSet.getImage(ImageSet.ImageSelect.PICTURE);
    }
    
    /*private ArrayList<Item> populateListWithItems() 
   {
        ArrayList<Item> list = new ArrayList<Item>();
        ArrayList<ImageInfo> foodImages = ImageSet.getImages(ImageSet.ImagesSelect.FOOD);
        double basePrice = 1.99;
        double price;
        DecimalFormat doubleFormater = new DecimalFormat("#.##");
        for(int i = 0; i<foodImages.size(); i++)
        {
            Item item = new Item(foodImages.get(i));
            item.setId(i+1);
            price = (0.63+basePrice+(i+1)*basePrice/2);
            item.setPrice( Double.valueOf(doubleFormater.format(price+price*0.05)) );
            item.setQuantity(10);
            list.add(item);
        }
        return list;
    }*/
    
    private LinkedHashMap <String, Integer> populateToDoList()
    {
        LinkedHashMap <String, Integer> list = new LinkedHashMap <String, Integer>();
        list.put("Buy groceries",1);
        list.put("Review picture of the day",2);
        list.put("Check calendar",5);
        return list;
    }

   /* public ImageInfo getImage() {
        return image;
    }

    public void setImage(ImageInfo image) {
        this.image = image;
    }*/

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LinkedHashMap<String, Integer> getToDoList() {
        return toDoList;
    }

    public void setToDoList(LinkedHashMap<String, Integer> toDoList) {
        this.toDoList = toDoList;
    }
}