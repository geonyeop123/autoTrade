package autoTrade.api.controller.position;

import autoTrade.api.controller.position.request.PositionCreateControllerRequest;
import autoTrade.api.service.member.MemberService;
import autoTrade.api.service.position.PositionService;
import autoTrade.domain.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PositionController.class)
class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PositionService positionService;

    @MockBean
    private MemberService memberService;

    @DisplayName("포지션을 생성할 수 있다.")
    @Test
    void createPosition() throws Exception{
        // given

        Member dummyMember = Member.builder().id(1L).name("이건엽").build();

        when(memberService.findById(1L)).thenReturn(dummyMember);

        PositionCreateControllerRequest request = PositionCreateControllerRequest.builder()
                .memberId(1L)
                .symbol("BTC")
                .krPrice("146529000")
                .krQuantity(0.202)
                .usPrice("99653.3")
                .usQuantity(0.202)
                .exchangeRate(1450)
                .build();


        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/position/save")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("포지션을 생성할 때 존재하지 않는 회원인 경우 생성이 되지 않는다.")
    @Test
    void createPositionWithoutMember() throws Exception{

        when(memberService.findById(1L)).thenThrow(new IllegalArgumentException("존재하지 않는 회원입니다."));

        // given
        PositionCreateControllerRequest request = PositionCreateControllerRequest.builder()
                .memberId(1L)
                .symbol("BTC")
                .krPrice("146529000")
                .krQuantity(0.202)
                .usPrice("99653.3")
                .usQuantity(0.202)
                .exchangeRate(1450)
                .build();


        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/position/save")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}