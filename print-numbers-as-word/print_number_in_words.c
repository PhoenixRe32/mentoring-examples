#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* get_word_for_units(const int num_units) {
  switch(num_units) {
    case 0:
      return "";
    case 1:
      return "one";
    case 2:
      return "two";
    case 3:
      return "three";
    case 4:
      return "four";
    case 5:
      return "five";
    case 6:
      return "six";
    case 7:
      return "seven";
    case 8:
      return "eight";
    case 9:
      return "nine";
  }

  exit(-1);
}

char* get_word_for_tens(const int num_tens) {
  switch(num_tens) {
    case 0:
      return "";
    case 1:
      return "ten";
    case 2:
      return "twenty";
    case 3:
      return "thirty";
    case 4:
      return "fourty";
    case 5:
      return "fifty";
    case 6:
      return "sixty";
    case 7:
      return "seventy";
    case 8:
      return "eighty";
    case 9:
      return "ninety";
  }

  exit(-1);
}

int is_it_special_number(const int number) {
  switch(number) {
    case 0:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
      return 1;
  }

  return 0;
}

char* get_word_for_special_numbers(const int special_number) {
  switch(special_number) {
    case 0:
      return "zero";
    case 11:
    return "eleven";
    case 12:
    return "twelve";
    case 13:
    return "thriteen";
    case 14:
    return "fourteen";
    case 15:
    return "fifteen";
    case 16:
    return "sixteen";
    case 17:
    return "seventeen";
    case 18:
    return "eighteen";
    case 19:
    return "nineteen";
  }

  exit(-1);
}

char* get_connector(const char* tens_word, const char* units_word) {
  if (strlen(tens_word) > 0 && strlen(units_word) > 0 ) {
    return " ";
  }

  return "";
}

void print(int number) {
  if (is_it_special_number(number) == 1) {
    const char* special_number_word = get_word_for_special_numbers(number);
    printf("%s", special_number_word);
  } else {
    const int tens = number / 10;
    const char* tens_word = get_word_for_tens(tens);

    const int units = number - (tens * 10);
    const char* units_word = get_word_for_units(units);

    const char* connector = get_connector(tens_word, units_word);
    printf("%s%s%s", tens_word, connector, units_word);
  }

  printf("\n");
}


int main(void) {
  for (int i=0; i<100; i++) {
    print(i);
  }
}