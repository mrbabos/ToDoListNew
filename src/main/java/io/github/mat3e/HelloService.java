package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class HelloService {
    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1, "Hello","en");
    private LangRepository langRepository;
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);


    public HelloService(){
        this(new LangRepository());
    }
    public HelloService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    String prepareGreeting(String name){
        return prepareGreeting(name, null);
    }
    String prepareGreeting(String name, String lang){
        Integer langId;
        try {
             langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG.getId());
        }catch (NumberFormatException e){
            logger.warn("Non numeric language  is used: " + lang);
            langId = FALLBACK_LANG.getId();
        }

        var welcomeMsg = langRepository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMSG();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME) ;
        return welcomeMsg+ " " +  nameToWelcome + " !";
    }
}
