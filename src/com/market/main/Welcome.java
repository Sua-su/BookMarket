package com.market.main;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Scanner;
import com.market.bookitem.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import com.market.cart.Cart;
import com.market.member.Admin;
import com.market.member.User;
import com.market.execption.CartException;

public class Welcome {

    static final int NUM_BOOK = 5; // Fixed variable name to be consistent
    static final int NUM_ITEM = 7; // Fixed variable name to be consistent
    // static CartItem[] mCartItem = new CartItem[NUM_BOOK];
    // static int mCartCount = 0;
    static Cart mCart = new Cart();
    static User mUser;

    public static void main(String[] args) {
      //  Book[] mBookList = new Book[NUM_BOOK];
    	Book[] mBookList;
    	int mTotalBook = 0;

        Scanner input = new Scanner(System.in);

        System.out.print("당신의 이름을 입력하시오 : ");
        String userName = input.next();

        System.out.print("연락처를 입력하시오 : ");
        int userMobile = input.nextInt();

        mUser = new User(userName, userMobile);

        String a = "welcome to shopping mall";
        String b = "welcome to book market";

        boolean quit = false;
        while (!quit) {
            System.out.println("****************************");
            System.out.println("\t" + a);
            System.out.println("\t" + b);
            System.out.println("****************************");

            menuIntroduction();
            
            try {
                System.out.println("메뉴 번호를 선택해주세요");
                int n = input.nextInt();
                input.nextLine(); // Consume newline
                
                System.out.println(n + "번을 선택했습니다.");
                
                if (n < 1 || n > 9) {
                    System.out.println("1부터 9까지의 숫자를 입력하세요.");
                }
                else {
                    switch(n) {
                    case 1: menuGustInfo(userName, userMobile); break;
                    case 2: menuCartItemList(); break;
                    case 3: menuCartClear(); break;
                    case 4: 
                    	mTotalBook = totalFileToBookList();
                    	mBookList = new Book[mTotalBook];
                    	menuCartAddItem(mBookList); break;
                    case 5: menuCartRemoveItemCount(); break;
                    case 6: menuCartRemoveItem(); break;
                    case 7: menuCartBill(); break;
                    case 8: menuCartExit(); quit = true; break;
                    case 9: menuAdminLogin(); break;
                    }
                }
            } catch(CartException e) {
                System.out.println(e.getMessage());
                quit = true;
            } catch(Exception e) {
                System.out.println("올바르지 않은 메뉴 선택으로 종료합니다.");
                quit = true;
            }
        }
        input.close();
    }
    
    
    public static int totalFileToBookList() {
    	try {
    		
    		FileReader fr = new FileReader ("book.txt");
    		BufferedReader reader = new BufferedReader(fr);
    		
    		String str;
    		int num = 0;
    		while ((str = reader.readLine()) !=null){
    			if(str.contains("ISBN"))
    				++num;
    		}
    		
    		reader.close();
    		fr.close();
    		return num;
    	}catch (Exception e) {
    		System.out.println(e);
    	}
    	return 0;
    }
    public static void setFileToBookList(Book[] booklist) {
    	try {
    		FileReader fr = new FileReader("book.txt");
    		BufferedReader reader = new BufferedReader(fr);
    		
    		String str2;
    		String[] readBook = new String[7];
    		int count = 0;
    		
    		while ((str2 = reader.readLine()) != null) {
    	
    			
    			
    			if (str2.contains("ISBN")) {
    				readBook[0] = str2;
    				readBook[1]	= reader.readLine();
    				readBook[2]	= reader.readLine();
    				readBook[3]	= reader.readLine();
    				readBook[4]	= reader.readLine();
    				readBook[5]	= reader.readLine();
    				readBook[6]	= reader.readLine();
    				

    				}
    			booklist[count++] = new Book(readBook[0],readBook[1],Integer.parseInt(readBook[2]),
    					readBook[3], readBook[4], readBook[5],readBook[6]);
    			}
    			
    			
    		
    			reader.close();
    			fr.close();
    			}catch(Exception e ) {
    				System.out.println(e);
    	}
    }


    public static void menuIntroduction() {
        System.out.println("1.고객 정보 확인하기 \t4. 바구니 항목 추가하기");
        System.out.println("2.장바구니 상품 목록 보기 \t5. 장바구니 항목 수량 줄이기");
        System.out.println("3.장바구니 비우기 \t6. 장바구니 항목 삭제");
        System.out.println("7.영수증 표기하기 \t8. exit");
        System.out.println("9.관리자 로그인");
        System.out.println("****************************");
    }

    public static void menuGustInfo(String name, int mobile) {
        System.out.println("현재 고객 정보: ");
        System.out.println("이름: " + mUser.getName() + "     연락처: " + mUser.getPhone());
    }

    public static void menuCartItemList() {
        /*
         * System.out.println("장바구니 상품 목록 보기:  ");
         * System.out.println("------------------------------");
         * System.out.println("    도서ID \t|     수량 \t|        합계");
         * for (int i = 0; i < mCartCount; i++) {
         * System.out.println("        " + mCartItem[i].getBookID() + "\t|" +
         * "        " + mCartItem[i].getQuantity() + "\t|" +
         * "        " + mCartItem[i].getTotalPrice());
         * }
         * System.out.println("------------------------------");
         */

        if (mCart.mCartCount >= 0) {
            mCart.printCart();
        }
    }

    public static void menuCartClear() throws CartException {
        // System.out.println("장바구니 비우기: ");
        // Reset cart
        if (mCart.mCartCount == 0)
            throw new CartException("장바구니에 항목이 없습니다. ");
            //System.out.println("장바구니가 비어있습니다.");
        else {
            System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            if (str.toUpperCase().equals("Y")) {
                System.out.println("장바구니가 비워졌습니다.");
                mCart.deleteBook();

            }
        }

        /*
         * mCartCount = 0;
         * System.out.println("장바구니가 비워졌습니다.");
         */
    }

    // Method for String array books (legacy)
    public static void menuCartAddItem(Book[] booklist) {
        // System.out.println("장바구니 항목 추가하기: ");

        BookList(booklist);

        /*
         * for (int i = 0; i < NUM_BOOK; i++) {
         * for (int j = 0; j < NUM_ITEM; j++)
         * System.out.print(book[i][j] + " | ");
         * System.out.println();
         * }
         */

        mCart.printBooklist(booklist);

        boolean quit = false;

        while (!quit) {
            System.out.print("장바구니에 추가할 도서의 ID 입력: ");

            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            boolean flag = false;
            int numId = -1;

            for (int i = 0; i < NUM_BOOK; i++) {
                if (str.equals(booklist[i].getBookId())) {
                    numId = i;
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println("장바구니에 추가하시겠습니까? Y | N ");
                str = input.nextLine();
                if (str.equalsIgnoreCase("Y")) {
                    System.out.println(booklist[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");
                    if (!isCartInBook(booklist[numId].getBookId())) {
                        mCart.insertBook(booklist[numId]);
                    }
                }
                quit = true;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    // New method for Book array
    public static void menuCartAddItem(Book[] bookList, Scanner input) {
        System.out.println("장바구니 항목 추가하기: ");

        // Display book list
        for (int i = 0; i < NUM_BOOK; i++) {
            if (bookList[i] != null) {
                System.out.println(bookList[i].getBookId() + " | " +
                        bookList[i].getName() + " | " +
                        bookList[i].getUnitPrice() + " | " +
                        bookList[i].getAuthor() + " | " +
                        bookList[i].getDescription() + " | " +
                        bookList[i].getCategory() + " | " +
                        bookList[i].getReleatseDate());
            }
        }

        boolean quit = false;
        while (!quit) {
            System.out.print("장바구니에 추가할 도서의 ID 입력: ");
            String str = input.nextLine();

            boolean flag = false;
            int numId = -1;

            for (int i = 0; i < NUM_BOOK; i++) {
                if (bookList[i] != null && str.equals(bookList[i].getBookId())) {
                    numId = i;
                    flag = true;
                    break;
                }
            }

            if (flag) {
                System.out.println("장바구니에 추가하시겠습니까? Y | N ");
                str = input.nextLine();

                if (str.equalsIgnoreCase("Y")) {
                    System.out.println(bookList[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");
                    if (!isCartInBook(bookList[numId].getBookId())) {
                        // mCartItem[mCartCount++] = new CartItem(bookList[numID]);
                        mCart.insertBook(bookList[numId]);
                    }
                }
                quit = true;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    public static boolean isCartInBook(String bookId) {
        /*
         * boolean flag = false;
         * for (int i = 0; i < mCartCount; i++) {
         * if (bookId.equals(mCartItem[i].getBookID())) {
         * mCartItem[i].setQuantity(mCartItem[i].getQuantity() + 1);
         * flag = true;
         * }
         * }
         * return flag;
         */
        return mCart.isCartInBook(bookId);
    }

    public static void menuCartRemoveItemCount() {
        System.out.println("장바구니의 항목 수량 줄이기: ");
    }

    public static void menuCartRemoveItem() throws Exception {
        // System.out.println("장바구니의 항목 삭제하기: ");
        if (mCart.mCartCount == 0)
            throw new CartException("장바구니에 항목이 없습니다.");
            //System.out.println("장바구니가 비어있습니다.");
        else {
            menuCartItemList();
            boolean quit = false;
            while (!quit) {
                System.out.print("삭제할 도서의 ID 입력:");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                boolean flag = false;
                int numID = -1;

                for (int i = 0; i < mCart.mCartCount; i++) {
                    if (str.equals(mCart.mCartItem[i].getBookID())) {
                        numID = i;
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("장바구니에서 삭제하시겠습니까? Y | N ");
                    str = input.nextLine();

                    if (str.toUpperCase().equals("Y")) {
                        System.out.println(mCart.mCartItem[numID].getBookID() + " 도서가 장바구니에서 삭제되었습니다.");
                        mCart.removeCart(numID);
                    }
                    quit = true;
                } else {
                    System.out.println("다시 입력해주세요.");
                }
            }
        }
    }

    public static void menuCartBill() {
        // System.out.println("영수증 표시하기: ");
        if (mCart.mCartCount == 0)
            System.out.println("장바구니에 항목이 없습니다.");
        else {
            System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N ");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if (str.toUpperCase().equals("Y")) {
                System.out.println("배송지를 입력해주세요");
                String address = input.nextLine();
                printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
            } else {
                System.out.println("배송받을 고객명을 입력해주세요");
                String name = input.nextLine();
                System.out.println("배송받을 연락처를 입력해주세요");
                int phone = input.nextInt();
                System.out.println("배송지를 입력해주세요");
                String address = input.nextLine();
                printBill(name, str, address);
            }

        }
    }

    public static void printBill(String name, String phone, String address) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatter.format(date);
        System.out.println();
        System.out.println("--------------배송받을 고객 정보--------------");
        System.out.println("이름: " + name + "   \t\t연락처: " + phone);
        System.out.println("배송지: " + address + "\t\t 배송일: " + strDate);

        mCart.printCart();

        int sum = 0;
        for (int i = 0; i < mCart.mCartCount; i++) {
            sum += mCart.mCartItem[i].getTotalPrice();
        }
        System.out.println("\t\t\t총 금액: " + sum + "원\t");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public static void menuCartExit() {
        System.out.println("종료");
    }

    public static void menuAdminLogin() {
        System.out.println("관리자 정보 입력 하세요");

        Scanner input = new Scanner(System.in);
        System.out.print("Id: ");
        String adminId = input.next();

        System.out.print("Pw: ");
        String adminPW = input.next();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
            System.out.println("이름: " + admin.getName() + "    연락처: " + admin.getPhone());
            System.out.println("Id: " + admin.getId() + "       Pw: " + admin.getPassword());
            {
            	String[] writeBook = new String[7];
            	System.out.println("도서 정보를 추가하겠습니까? Y | N");
            	String str = input.next();
            	
            	if (str.toUpperCase().equals("Y")) {
            		Date date = new Date();
            		SimpleDateFormat formatter =  new SimpleDateFormat("yyMMddhhmmss");
            		String strDate = formatter.format(date);
            		writeBook[0] = "ISBN" + strDate;
            		System.out.println("도서 ID :" + writeBook[0]);
            		String st1 = input.nextLine();
            		System.out.print("도서명 : ");
            		writeBook[1] = input.nextLine();
            		System.out.print("가격 : ");
            		writeBook[2] = input.nextLine();
            		System.out.print("저자 : ");
            		writeBook[3] = input.nextLine();
            		System.out.print("설명 : ");
            		writeBook[4] = input.nextLine();
            		System.out.print("분야 : ");
            		writeBook[5] = input.nextLine();
            		System.out.print("출판일 : ");
            		writeBook[6] = input.nextLine();
            		
            		try {
            			FileWriter fw = new FileWriter("book.txt", true);
            			
            			for(int i =0; i<7; i++)
            				fw.write(writeBook[i]+"\n");
            				fw.close();
            				System.out.println("새 도서 정보 저장되었습니다.");
            		}catch (Exception e) {
            			System.out.println(e);
            		}
            	}else {
            		System.out.println("이름" +admin.getName() + "연락처"+admin.getPhone());
            		System.out.println("아이디"+ admin.getId() + "비밀번호"+ admin.getPassword());
            	}
            	
            }
        } else {
            System.out.println("관리자 정보 불일치");
        }
    }

    // New method for Book array
    public static void BookList(Book[] bookList) {
    	setFileToBookList(bookList);
    	/*
        bookList[0] = new Book("ISBN1234", "쉽게 배우는 웹 프로그래밍", 27000);
        bookList[0].setAuthor("송미영");
        bookList[0].setDescription("자바 프로그래밍");
        bookList[0].setCategory("IT 전문서");
        bookList[0].setReleatseDate("2023.01.15");

        bookList[1] = new Book("ISBN2345", "노트르담의 곱추", 33000);
        bookList[1].setAuthor("컴퓨터 사고");
        bookList[1].setDescription("컴퓨터 입문서");
        bookList[1].setCategory("실무 네트워크");
        bookList[1].setReleatseDate("2022.05.20");

        bookList[2] = new Book("ISBN3456", "스크래쳐", 31000);
        bookList[2].setAuthor("김프로그래머");
        bookList[2].setDescription("그날의 기억");
        bookList[2].setCategory("데이터 분석");
        bookList[2].setReleatseDate("2022.10.18");

        bookList[3] = new Book("ISBN4567", "자바의 정석", 30000);
        bookList[3].setAuthor("고프로그래머");
        bookList[3].setDescription("자바 완전 정복");
        bookList[3].setCategory("프로그래밍 언어");
        bookList[3].setReleatseDate("2021.12.10");

        bookList[4] = new Book("ISBN5678", "파이썬 기초", 26000);
        bookList[4].setAuthor("박데이터");
        bookList[4].setDescription("쉽게 배우는 파이썬");
        bookList[4].setCategory("프로그래밍 언어");
        bookList[4].setReleatseDate("2022.03.13");
    }*/
    	
    	
    	
    }
}