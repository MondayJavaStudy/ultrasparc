package c.w.q.study.homework.second;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortByNotesDate {

    final static String regexp = "[0-9]{2,4}([- /]|([년]))((0*[1-9]|[10-12])){1,2}([- /]|([월]))((0*[1-9]|1[0-9]|2[0-9]|3[0-1])){1,2}(([일])|[- /]*)";

    public List<String> sortMemo(List<String> notes) {
        Map<String, String> result = new HashMap<>();

        for(String note : notes) {
            // 문자열에서 날짜 형식 문자열 추출, 날짜 포멧변환
            String date = convertToDateFormat(find(note)).toString();
            result.put(date, note);
        }
        return getSortedMemo(result);
    }

    public List<String> getSortedMemo(Map<String, String> memos) {
        Map<String, String> treeMap = new TreeMap<>(memos);
        List<String> sortedList = new ArrayList<>();

        for(Map.Entry<String, String> entry: treeMap.entrySet()) {
            sortedList.add(entry.getValue());
//            System.out.println(">>> " + entry.getValue());
        }
        return sortedList;
    }

    private LocalDate convertToDateFormat(String sdate) {
        final String ymd = "[- / 년 월 일]";
        int[] y4m2d2 = parseY4M2D2(sdate.split(ymd));

        return LocalDate.of(y4m2d2[0], y4m2d2[1], y4m2d2[2]);
    }

    private int[] parseY4M2D2(String[] yyyymmdd) {
        if (yyyymmdd.length < 3) {
            return null;
        }

        int[] y4m2d2 = new int[3];

        String yyyy = "";
        if (yyyymmdd[0].length() == 2) {
            yyyy = "20";
        }
        y4m2d2[0] = Integer.parseInt(yyyy.concat(yyyymmdd[0]));
        y4m2d2[1] = Integer.parseInt(yyyymmdd[1]);
        y4m2d2[2] = Integer.parseInt(yyyymmdd[2]);

        return y4m2d2;
    }

    private String find(String note) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(note);

        if (!matcher.find())
            return null;

        return matcher.group();
    }
}

