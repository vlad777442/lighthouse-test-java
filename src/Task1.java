import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1 {
    public static class ProfileInfo {
        public UserInfo userInfo;
        public CompanyInfo companyInfo;
    }

    public static class UserInfo {
        public String name;
        public String age;
    }

    public static class CompanyInfo {
        public String id;
        public String companyName;
    }

    private ExecutorService executorService;

    public void ProfileService() {
        executorService = Executors.newFixedThreadPool(4);
    }
    public static void main(String[] args) {
        ProfileInfo profileInfo = getProfileInfo(1L);
        System.out.println(profileInfo.userInfo.name);
        System.out.println(profileInfo.userInfo.age);
        System.out.println(profileInfo.companyInfo.id);
        System.out.println(profileInfo.companyInfo.companyName);
    }

    public static ProfileInfo getProfileInfo(Long id) {
        // TODO необходимо написать реализацию
        CompletableFuture<UserInfo> userInfoFuture = CompletableFuture.supplyAsync(() -> getUserInfo(id));
        CompletableFuture<CompanyInfo> companyInfoFuture = CompletableFuture.supplyAsync(() -> getCompanyInfo(id));
        ProfileInfo profileInfo = new ProfileInfo();
        try {
            UserInfo userInfo = userInfoFuture.get();
            CompanyInfo companyInfo = companyInfoFuture.get();

            profileInfo.userInfo = userInfo;
            profileInfo.companyInfo = companyInfo;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return profileInfo;
    }

    private static UserInfo getUserInfo(Long id) {
        // вызов внешнего сервиса занимает ~ 1 секунду
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.name = "Mike";
        userInfo.age = "26";
        return userInfo;
    }

    private static CompanyInfo getCompanyInfo(Long id) {
        // вызов внешнего сервиса занимает ~ 1 секунду
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.id = "1";
        companyInfo.companyName = "Midea";
        return companyInfo;
    }

}
