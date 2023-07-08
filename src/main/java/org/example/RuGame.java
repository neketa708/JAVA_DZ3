package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class RuGame extends AbstractGame{
    @Override
    public List<String> generateCharList() {

        List<String> strList = new ArrayList<>();
        for (int i = 'а'; i <= 'я'; i++) {
            strList.add(Character.toString(i));
        }
        return strList;
    }

    @Override
    public String getNameGame() {
        List<String> tmp = Arrays.asList(getClass().getName().split("\\."));
        return tmp.get(tmp.size() - 1);
    }
}
