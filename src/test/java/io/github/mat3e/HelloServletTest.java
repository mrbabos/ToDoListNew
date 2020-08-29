package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServletTest {
    private static final String WELCOME = "hello";
    private static final String FALLBACK_ID_WELCOME = "Priviet";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName(){
        // given
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + " !" , result);

    }
    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        // given
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        // when
        var result = SUT.prepareGreeting(name, "-1" );
        // then
        assertEquals(WELCOME + " " + name + " !", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackLang(){
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "abc");
        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + " !", result);
    }
    @Test
    public void test_prepareGreeting_TextLang_returnsGreetingWithFallbackIdLang(){
        // given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);
        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + " !", result);
    }
    private LangRepository alwaysReturningLangRepository(){
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id){
                return Optional.of(new Lang(id,WELCOME,null));
            }
        };
    }
    private LangRepository fallbackLangIdRepository(){
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id){
                if(id.equals(HelloService.FALLBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
}
