/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

/**
 *
 * @author ASUS
 */
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public final class CheckError {

    public static boolean isInteger(String numberString) {
        try {
            Integer.parseInt(numberString);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isFloat(String numberString) {
        try {
            Float.parseFloat(numberString);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static int validateSelectedOption(String selectedString) {
        if (CheckError.isInteger(selectedString)) {
            return Integer.parseInt(selectedString);
        } else {
            // Return số lớn để nhảy vào case default của switch case và báo lỗi
            return Integer.MAX_VALUE;
        }
    }

    /*
        [REPLACE TIẾNG VIỆT]
     */
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
        'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
        'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
        'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
        'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
        'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
        'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
        'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
        'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
        'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
        'ữ', 'Ự', 'ự', 'ỹ', 'Ỹ', 'ỷ', 'Ỷ'};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
        'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
        'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
        'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
        'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u', 'U', 'u', 'y', 'Y', 'y', 'Y'};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

//    public static String removeAccent(String str) {
//        StringBuilder sb = new StringBuilder(str);
//        for (int i = 0; i < sb.length(); i++) {
//            sb.setCharAt(i, removeAccent(sb.charAt(i)));
//        }
//        return sb.toString();
//    }
    public static String removeAccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    /*
        [REGEX FOR STUDENT]
     */
    public static boolean isNameStudent(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return removeAccent(str).matches(expression);
    }

    public static boolean isBirthdayStudent(String str) {
        return isValidDate(str);
    }

    public static boolean isGenderStudent(String str) {
        if (str.equals("Nam") || str.equals("Nu")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNumberStudent(String str) {
        String expression = "^\\d{10,11}$";
        return str.matches(expression);
    }

    public static boolean isMajorStudent(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return removeAccent(str).matches(expression);
    }

    public static boolean isCohortStudent(String str) {
        String expression = "^\\d{4}[-]\\d{4}$";
        if (str.matches(expression)) {
            String[] split_str = str.split("-");
            if (Integer.parseInt(split_str[1]) >= Integer.parseInt(split_str[0])) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isCohortExam(String str) {
        String expression = "^\\d{4}[-]\\d{4}$";
        if (str.matches(expression)) {
            String[] split_str = str.split("-");
            if (Integer.parseInt(split_str[1]) - Integer.parseInt(split_str[0]) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static boolean isMyclassStudent(String str) {
        String expression = "^[A-Za-z]\\d[-]$";
        return str.matches(expression);
    }

    /*
        [REGEX FOR LECTURER]
     */
    public static boolean isNameLecturer(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return removeAccent(str).matches(expression);
    }

    public static boolean isBirthdayLecturer(String str) {
        return isValidDate(str);
    }

    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
            df.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean isGenderLecturer(String str) {
        if (str.equals("Nam") || str.equals("Nu")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNumberLecturer(String str) {
        String expression = "^\\d{10,11}$";
        return str.matches(expression);
    }

    /*
        [REGEX FOR SUBJECT]
     */
    public static boolean isNameSubject(String str) {
        String expression = "^[a-zA-Z0-9\\-:/()\\s]+";
        return removeAccent(str).matches(expression);
    }

    public static boolean isNameClass(String str) {
        String expression = "^[a-zA-Z0-9\\s]+";
        return removeAccent(str).matches(expression);
    }

    /*
        [CHECKING ID]
     */
    public static boolean checkStudent(String str) {
        String strTest = str.toUpperCase();
        String regex = "SV\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(strTest).matches();
    }

    public static boolean checkLecturer(String str) {
        String strTest = str.toUpperCase();
        String regex = "GV\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(strTest).matches();
    }

    public static boolean checkSubject(String str) {
        String strTest = str.toUpperCase();
        String regex = "HP\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(strTest).matches();
    }
}
