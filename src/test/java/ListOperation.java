import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author dxm
 * @Date 2022/8/30
 */
public class ListOperation {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        for (Integer num : list) {
            if (num == 3) {
                list.remove(num);
            }
        }
    }
}
