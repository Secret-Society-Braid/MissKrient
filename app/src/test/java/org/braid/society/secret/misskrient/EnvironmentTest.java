package org.braid.society.secret.misskrient;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnvironmentTest {

  @Mock
  List<Integer> mockedList;

  @Test
  void test() {
    when(mockedList.add(anyInt())).thenReturn(true, false);

    assertTrue(mockedList.add(1));

    assertThat(mockedList.add(2)).isFalse();

    verify(mockedList, times(2)).add(anyInt());
  }
}
