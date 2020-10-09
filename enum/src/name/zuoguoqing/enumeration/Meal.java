/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file Meal.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午1:15:49
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午1:15:49
 */
public enum Meal {
    APPETIZER(Food.Appetizer.class), 
    MAINCOURSE(Food.MainCourse.class), 
    DESSERT(Food.Dessert.class), 
    COFFEE(Food.Coffee.class);

    private Food[] foods;

    private Meal(Class<? extends Food> cls) {
        foods = cls.getEnumConstants();
    }

    public interface Food {
        enum Appetizer implements Food {
            SALAD, SOUP, SPRING_ROLLS;
        }

        enum MainCourse implements Food {
            LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;
        }

        enum Dessert implements Food {
            TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;
        }

        enum Coffee implements Food {
            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;
        }
    }

    public Food randSelection() {
        return Enums.random(foods);
    }

    public static void main(String[] args) {
        IntStream.range(0, 7).forEach(i -> {
            Arrays.stream(Meal.values()).forEach(meal -> {
                System.out.println(meal.randSelection());
            });
            System.out.println("**************");
        });
    }

}
