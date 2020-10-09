/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.enumeration
 * @file RockPaperScissors.java
 * @author ZuoGuoqing
 * @date 2020年10月3日 下午5:18:22
 * @version 1.0
 */
package name.zuoguoqing.enumeration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;
import java.util.stream.IntStream;

enum Outcome {
    WIN, LOSE, DRAW;
}

/**
 *
 * @author ZuoGuoqing
 * @version 1.0
 * @date 2020年10月3日 下午5:18:22
 */
public class RockPaperScissors {
    /**
     * 使用常规switch和enum实现多路分发
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2020年10月3日 下午5:45:49
     */
    static class PatternOne {
        private enum Item {
            ROCK, PAPER, SCISSORS;
        }

        private static Outcome eval(Item item1, Item item2) {
            switch (item1) {
            case ROCK:
                switch (item2) {
                case ROCK:
                    return Outcome.DRAW;
                case PAPER:
                    return Outcome.LOSE;
                case SCISSORS:
                    return Outcome.WIN;
                }
            case PAPER:
                switch (item2) {
                case ROCK:
                    return Outcome.WIN;
                case PAPER:
                    return Outcome.DRAW;
                case SCISSORS:
                    return Outcome.LOSE;
                }
            case SCISSORS:
                switch (item2) {
                case ROCK:
                    return Outcome.LOSE;
                case PAPER:
                    return Outcome.WIN;
                case SCISSORS:
                    return Outcome.DRAW;
                }
            }

            return null;
        }

        private static void match(Item item1, Item item2) {
            System.out.println(item1 + " VS. " + item2 + " : " + eval(item1, item2));
        }

        public static void run(int num) {
            IntStream.range(0, num).forEach(i -> {
                match(Enums.random(Item.class), Enums.random(Item.class));
            });
        }
    }

    /**
     * 使用接口实现多路分发
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2020年10月3日 下午6:12:00
     */
    static class PatternTwo {
        private static interface Item {
            // 多路分发
            Outcome complete(Item item);

            // 评估结果
            Outcome eval(Rock rock);

            Outcome eval(Paper paper);

            Outcome eval(Scissors scissors);
        }

        private static class Rock implements Item {

            @Override
            public Outcome complete(Item item) {
                return item.eval(this);
            }

            @Override
            public Outcome eval(Rock rock) {
                return Outcome.DRAW;
            }

            @Override
            public Outcome eval(Paper paper) {
                return Outcome.WIN;
            }

            @Override
            public Outcome eval(Scissors scissors) {
                return Outcome.LOSE;
            }

            @Override
            public String toString() {
                return "Rock";
            }

        }

        private static class Paper implements Item {

            @Override
            public Outcome complete(Item item) {
                return item.eval(this);
            }

            @Override
            public Outcome eval(Rock rock) {
                return Outcome.LOSE;
            }

            @Override
            public Outcome eval(Paper paper) {
                return Outcome.DRAW;
            }

            @Override
            public Outcome eval(Scissors scissors) {
                return Outcome.WIN;
            }

            @Override
            public String toString() {
                return "Paper";
            }

        }

        private static class Scissors implements Item {

            @Override
            public Outcome complete(Item item) {
                return item.eval(this);
            }

            @Override
            public Outcome eval(Rock rock) {
                return Outcome.WIN;
            }

            @Override
            public Outcome eval(Paper paper) {
                return Outcome.LOSE;
            }

            @Override
            public Outcome eval(Scissors scissors) {
                return Outcome.DRAW;
            }

            @Override
            public String toString() {
                return "Scissors";
            }

        }

        private static void match(Item item1, Item item2) {
            System.out.println(item1 + " VS. " + item2 + " : " + item1.complete(item2));
        }

        private static Item randItem() {
            Random random = new Random();
            switch (random.nextInt(3)) {
            case 0:
                return new Rock();
            case 1:
                return new Paper();
            case 2:
                return new Scissors();
            }
            return null;
        }

        public static void run(int num) {
            IntStream.range(0, num).forEach(i -> {
                match(randItem(), randItem());
            });
        }
    }

    /**
     * 枚举常量函数实现多路分发
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2020年10月3日 下午6:37:59
     */
    static class PatternThree {
        private static enum Item {
            ROCK {
                @Override
                public Outcome eval(Item p) {
                    switch (p) {
                    case ROCK:
                        return Outcome.DRAW;
                    case SCISSORS:
                        return Outcome.WIN;
                    case PAPER:
                        return Outcome.LOSE;
                    }
                    return null;
                }
            },

            SCISSORS {
                @Override
                public Outcome eval(Item p) {
                    switch (p) {
                    case ROCK:
                        return Outcome.LOSE;
                    case SCISSORS:
                        return Outcome.DRAW;
                    case PAPER:
                        return Outcome.WIN;
                    }
                    return null;
                }
            },

            PAPER {
                @Override
                public Outcome eval(Item p) {
                    switch (p) {
                    case ROCK:
                        return Outcome.WIN;
                    case SCISSORS:
                        return Outcome.LOSE;
                    case PAPER:
                        return Outcome.DRAW;
                    }
                    return null;
                }
            };

            public abstract Outcome eval(Item p);

        }

        private static void match(Item item1, Item item2) {
            System.out.println(item1 + " VS. " + item2 + " : " + item1.eval(item2));
        }

        public static void run(int num) {
            IntStream.range(0, num).forEach(i -> {
                match(Enums.random(Item.class), Enums.random(Item.class));
            });
        }
    }

    /**
     * EnumMap实现多路分发
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2020年10月3日 下午6:45:37
     */
    static class PatternFour {
        private static enum Item {
            ROCK, SCISSORS, PAPER;
        }

        private static EnumMap<Item, EnumMap<Item, Outcome>> table = new EnumMap<>(Item.class);
        
        static {
            initTable();
        }
        
        private static void initTable() {
            Arrays.stream(Item.values()).forEach(item -> {
                table.put(item, new EnumMap<>(Item.class));
            });
            
            initRow(Item.ROCK, Outcome.DRAW, Outcome.WIN, Outcome.LOSE);
            initRow(Item.SCISSORS, Outcome.LOSE, Outcome.DRAW, Outcome.WIN);
            initRow(Item.PAPER, Outcome.WIN, Outcome.LOSE, Outcome.DRAW);
        }
        
        private static void initRow(Item item, Outcome o1, Outcome o2, Outcome o3) {
            EnumMap<Item, Outcome> row = table.get(item);
            row.put(Item.ROCK, o1);
            row.put(Item.SCISSORS, o2);
            row.put(Item.PAPER, o3);
        }
        
        private static void match(Item item1, Item item2) {
            System.out.println(item1 + " VS. " + item2 + " : " + table.get(item1).get(item2));
        }

        public static void run(int num) {
            IntStream.range(0, num).forEach(i -> {
                match(Enums.random(Item.class), Enums.random(Item.class));
            });
        }
    }
    
    /**
     * 使用多维数组实现多路分发
     *
     * @author ZuoGuoqing
     * @version 1.0
     * @date 2020年10月3日 下午7:17:46
     */
    static class PatternFive {
        private static enum Item {
            ROCK, SCISSORS, PAPER;
        }
        
        private static Outcome[][] table = {
                {Outcome.DRAW, Outcome.WIN, Outcome.LOSE},
                {Outcome.LOSE, Outcome.DRAW, Outcome.WIN},
                {Outcome.WIN, Outcome.LOSE, Outcome.DRAW}
        };
        
        private static void match(Item item1, Item item2) {
            System.out.println(item1 + " VS. " + item2 + " : " + table[item1.ordinal()][item2.ordinal()]);
        }

        public static void run(int num) {
            IntStream.range(0, num).forEach(i -> {
                match(Enums.random(Item.class), Enums.random(Item.class));
            });
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 15;
        PatternOne.run(n);
        PatternTwo.run(n);
        PatternThree.run(n);
        PatternFour.run(n);
        PatternFive.run(n);
    }

}
