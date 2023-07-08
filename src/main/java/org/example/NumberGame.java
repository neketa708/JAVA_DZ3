package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class NumberGame extends AbstractGame{
    @Override
    public List<String> generateCharList() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strList.add(Integer.toString(i));
        }
        return strList;
    }

    @Override
    public String getNameGame() {
        List<String> tmp = Arrays.asList(getClass().getName().split("\\."));
        return tmp.get(tmp.size() - 1);
    }
}
