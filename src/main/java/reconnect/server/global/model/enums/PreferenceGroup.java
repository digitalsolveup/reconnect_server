package reconnect.server.global.model.enums;

import java.util.Map;

public enum PreferenceGroup {
    UNDER_10("놀이공원", "키즈카페"),
    TEENAGER("카페", "디저트 카페"),
    TWENTIES("헬스장", "쇼핑몰"),
    THIRTIES("레스토랑", "레스토랑"),
    FORTIES("펍", "카페"),
    FIFTIES("공원", "공원"),
    SIXTIES("카페", "카페"),
    SEVENTIES("문화센터", "요가센터"),
    OVER_EIGHTY("노인복지관", "노인복지관");

    private final String maleKeyword;
    private final String femaleKeyword;

    PreferenceGroup(String maleKeyword, String femaleKeyword) {
        this.maleKeyword = maleKeyword;
        this.femaleKeyword = femaleKeyword;
    }

    private static final Map<Integer, PreferenceGroup> ageGroupMap = Map.of(
            10, UNDER_10,
            20, TEENAGER,
            30, TWENTIES,
            40, THIRTIES,
            50, FORTIES,
            60, FIFTIES,
            70, SIXTIES,
            80, SEVENTIES
    );

    public static PreferenceGroup fromAge(int age) {
        return ageGroupMap.entrySet().stream()
                .filter(entry -> age < entry.getKey())
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(OVER_EIGHTY);
    }

    public String getKeyword(Gender gender) {
        return gender == Gender.MALE ? maleKeyword : femaleKeyword;
    }
}
