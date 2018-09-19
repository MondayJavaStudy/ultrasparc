package c.w.q.study.homework.second

import spock.lang.Shared
import spock.lang.Specification

class SortByNotesDateSpec extends Specification {

    @Shared
    SortByNotesDate sortByNotesDate

    def setup() {
        sortByNotesDate = new SortByNotesDate()
    }

    def "주어진 메모에서 날짜 형식을 정상적으로 추출할 수 있어야 한다"() {
        given: "날짜 형식을 포함한 메모 준비"

        List<String> notes = new ArrayList<>()
        notes.add("2018/7/3 배민은행의 구조도를 손에 넣었다. 필요한 장비는 이런거 저런거 해서 각자 준비해야한다.")
        notes.add("타깃 은행은 잠실에 있는 배민은행으로 한다. 18년06월13일")
        notes.add("배민은행앞 별다방에서 18-06-12에 만나기로 함")

        when: "날짜 형식을 포함한 메모를 인자로 던져 날짜 형식만 추출해서 List<String> 형태로 반환"
        List<String> sortedNotes = sortByNotesDate.sortMemo(notes)
        sortedNotes.stream().forEach{note -> System.out.println(">>> ${note}")}

        then: "날짜가 정상적으로 추출되고, 정렬됐는지 확인"
        sortedNotes.get(0) == "배민은행앞 별다방에서 18-06-12에 만나기로 함"
        sortedNotes.get(1) == "타깃 은행은 잠실에 있는 배민은행으로 한다. 18년06월13일"
        sortedNotes.get(2) == "2018/7/3 배민은행의 구조도를 손에 넣었다. 필요한 장비는 이런거 저런거 해서 각자 준비해야한다."
    }

    def cleanup() {
        sortByNotesDate = null
    }
}
