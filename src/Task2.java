import java.util.*;

public class Task2 {
    static public class Profile {
        public Long id;
        public Long orgId;
        public Long groupId;

        @Override
        public String toString() {
            return "Person{"
                    + "id='" + id + '\''
                    + ", orgId=" + orgId
                    + ", groupId=" + groupId
                    + '}';
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Profile> arrayList = new ArrayList<>();
        String input;
        String[] s;

        while (in.hasNextLine()) {
            Profile profile = new Profile();
            input = in.nextLine();
            s = input.replaceAll("\\[|\\]", "").split(",");
            if (input.isEmpty()) break;

            profile.id = Long.parseLong(s[0].trim());
            profile.orgId = Long.parseLong(s[1].trim());
            profile.groupId = Long.parseLong(s[2].trim());

            arrayList.add(profile);
        }

        Map<Long, Map<Long, List<Profile>>> tmp = groupByOrgIdAndGroupId(arrayList);
        System.out.println(tmp);

    }

    public static Map<Long, Map<Long, List<Profile>>> groupByOrgIdAndGroupId(List<Profile> data) {
        // TODO необходимо написать реализацию
        Map<Long, Map<Long, List<Profile>>> res = new HashMap<>();
        Long orgId;
        Long groupId;

        for (int i = 0; i < data.size(); i++) {
            orgId = data.get(i).orgId;
            groupId = data.get(i).groupId;

            res.putIfAbsent(orgId, new HashMap<>());
            res.get(orgId).putIfAbsent(groupId, new ArrayList<>());
            res.get(orgId).get(groupId).add(data.get(i));
        }

        return res;
    }


}
