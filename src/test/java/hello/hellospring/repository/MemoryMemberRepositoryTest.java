package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hello.hellospring.service.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemberService memberService;
    MemoryMemberRepository memoryRepository;

    @BeforeEach
    public void beforeEach(){
        memoryRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryRepository);
    }


    @AfterEach
    public void afterEach(){
        memoryRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memoryRepository.save(member);

        Member result = memoryRepository.findById(member.getId()).get();
        //Assertions.assertEquals(member, null);
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        memoryRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryRepository.save(member2);

        Member result = memoryRepository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }



    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memoryRepository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        memoryRepository.save(member2);

        List<Member> result = memoryRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
