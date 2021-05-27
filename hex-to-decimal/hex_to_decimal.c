#include <stdio.h>
#include <string.h>
#include <math.h>

struct char_int_pair {
  char key;
  int value;
};

const struct char_int_pair lookup[] = {
  {'0', 0},
  {'1', 1},
  {'2', 2},
  {'3', 3},
  {'4', 4},
  {'5', 5},
  {'6', 6},
  {'7', 7},
  {'8', 8},
  {'9', 9},
  {'A', 10},
  {'B', 11},
  {'C', 12},
  {'D', 13},
  {'E', 14},
  {'F', 15},
};
const int lookup_length = sizeof(lookup) / sizeof(lookup[0]);
// const int lookup_length = sizeof(lookup) / sizeof(struct char_int_pair);

int map_hex_to_decimal_value(char character){
  int decimal_value_of_hex = 0;
  for(int i = 0; i < lookup_length; i++){
    if(lookup[i].key == character){
      decimal_value_of_hex = lookup[i].value;
    }
  }
  return decimal_value_of_hex;
}


int convert_hex_to_decimal(const char * hex){
  int length = strlen(hex);

  int result = 0;

  for(int i =0; i<length; i++){
    const int decimal_value = map_hex_to_decimal_value(hex[i]);
    result += decimal_value * pow(16, length - (i+1));
  }
return result;
}

int main(void){
  char hex[6] = "D3E5C";
  int value= convert_hex_to_decimal(hex);
  printf("The hex value %s is %d in decimal\n", hex, value);

  return 0;
}