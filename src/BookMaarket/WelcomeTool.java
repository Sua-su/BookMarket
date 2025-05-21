package BookMaarket;

import java.util.Scanner;

public class WelcomeTool {

    static final int Num_Book = 3;
    static final int Num_Item = 7;

    public static void main(String[] args) {
        String[][] mBook = new String[Num_Book][Num_Item];

        Scanner input = new Scanner(System.in);

        System.out.print("당신의 이름을 입력하시오 : ");
        String userName = input.next();

        System.out.print("연락처를 입력하시오 : ");
        int userMobile = input.nextInt();

        String a = "welcome to shopping mall";
        String b = "welcome to book market";

        boolean quit = false;
        while (!quit) {
            System.out.println("****************************");
            System.out.println("\t" + a);
            System.out.println("\t" + b);
            System.out.println("****************************");

            menuIntroduction();

            System.out.print("메뉴 번호 선택하세요 : ");
            int n = input.nextInt();
            input.nextLine(); // Consume the newline character

            System.out.println(n + "번을 선택했습니다.");

            if (n < 1 || n > 8) {
                System.out.println(n + "번은 유효하지 않은 선택입니다. 다시 시도하세요.");
            } else {
                switch (n) {
                    case 1:
                        menuGustInfo(userName, userMobile);
                        break;
                    case 2:
                        menuCarItemList();
                        break;
                    case 3:
                        menuCartClear();
                        break;
                    case 4:
                        menuCartAddItem(mBook, input);
                        break;
                    case 5:
                        menuCartRemoveItemCount();
                        break;
                    case 6:
                        menuCartRemoveItem();
                        break;
                    case 7:
                        menuCartBill();
                        break;
                    case 8:
                        menuCartExit();
                        quit = true;
                        break;
                }
            }
        }
        input.close();
    }

    public static void menuIntroduction() {
        System.out.println("1.고객 정보 확인하기 \t4. 바구니 정보 확인");
        System.out.println("2.장바구니 상품 목록 보기 \t5. 장바구니 항목 수량 줄이기");
        System.out.println("3.장바구니 비우기 \t6. 장바구니 항목 삭제");
        System.out.println("7.영수증 표기하기 \t8. exit");
        System.out.println("****************************");
    }

    public static void menuGustInfo(String name, int mobile) {
        System.out.println("현재 고객 정보: ");
        System.out.println("이름: " + name + " 연락처: " + mobile);
    }

    public static void menuCarItemList() {
        System.out.println("장바구니 상품 목록 보기: ");
    }

    public static void menuCartClear() {
        System.out.println("장바구니 비우기: ");
    }

    public static void menuCartAddItem(String[][] book, Scanner input) {
        BookList(book);

        for (int i = 0; i < Num_Book; i++) {
            for (int j = 0; j < Num_Item; j++) {
                System.out.print(book[i][j] + " | ");
            }
            System.out.println();
        }

        boolean quit = false;
        while (!quit) {
            System.out.print("장바구니에 추가할 도서의 ID 입력: ");
            String str = input.nextLine();

            boolean flag = false;
            int numID = -1;

            for (int i = 0; i < Num_Book; i++) {
                if (str.equals(book[i][0])) {
                    numID = i;
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println("장바구니에 추가하시겠습니까? Y | N ");
                str = input.nextLine();

                if (str.equalsIgnoreCase("Y")) {
                    System.out.println(book[numID][0] + " 도서가 장바구니에 추가되었습니다.");
                }
                quit = true;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    public static void menuCartRemoveItemCount() {
        System.out.println("장바구니의 항목 수량 줄이기: ");
    }

    public static void menuCartRemoveItem() {
        System.out.println("장바구니의 항목 삭제하기: ");
    }

    public static void menuCartBill() {
        System.out.println("영수증 표시하기: ");
    }

    public static void menuCartExit() {
        System.out.println("종료");
    }

    public static void BookList(String[][] book) {
        book[0][0] = "ISBN1234";
        book[0][1] = "쉽게 배우는 웹 프로그래밍";
        book[0][2] = "개미";
        book[0][3] = "송미영";
        book[0][4] = "자바 프로그래밍";
        book[0][5] = "IT 전문서";
        book[0][6] = "어렵지 않은 네트워크";

        book[1][0] = "ISBN5678";
        book[1][1] = "노트르담의 곱추";
        book[1][2] = "332244";
        book[1][3] = "컴퓨터 사고";
        book[1][4] = "컴퓨터 입문서";
        book[1][5] = "실무 네트워크";
        book[1][6] = "정보처리산업기사";

        book[2][0] = "ISBN9101";
        book[2][1] = "스크래쳐";
        book[2][2] = "컴퓨터 사고력";
        book[2][3] = "2000.03.24";
        book[2][4] = "그날의 기억";
        book[2][5] = "데스노트";
        book[2][6] = "맘마미아";
    }
}