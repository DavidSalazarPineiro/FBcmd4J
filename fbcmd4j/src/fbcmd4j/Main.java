package fbcmd4j;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class Main {

    
    public static void main(String[] args) {
    
     String mUserToken = "EAAJvzPF27jABABkswHrzZBjMN99cj5m1DexFI6FvmZCFlHjAtL41rBnwzMlV7jVzdMVBAuMgmoby91nIE1sHY8s4NwZAYFBmTx47OjSzZC5y0f0HtQH5VNwA94gc6Cr3O5XH2mj2iGpgsNXQAPfbKINZAQafAz0UorKyuNQvVDvs1aq5eemQLpKJEtpwA7xl5PhMZCl6J96gZDZD";   
     String mAppToken = "685875968536112|dWo3g-px6alDz6HrxzcR3meJzrM";
     String mAppID = "685875968536112";
     String mAppSecret = "4cdec36c23ed9eaa8bbd05ba4dfee8e1";
     
     Facebook mFacebook;
     
     mFacebook = new FacebookFactory().getInstance();
     mFacebook.setOAuthAppId(mAppID, mAppSecret);
     mFacebook.setOAuthAccessToken(new AccessToken(mUserToken));
     
   
     
    }
}
    
 

    



