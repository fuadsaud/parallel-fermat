#!/usr/bin/env bats

fermat="java -classpath .:../pj2.jar pj2 Fermat"

@test "garbage input" {
  result="$($fermat garbage)"
  [ "$result" -eq 1 ]
}

@test "2147483648" {
  result="$($fermat 2147483648)"
  [ "$result" -eq 2 ]
}

@test "524287" {
  result="$($fermat 524287)"
  [ "$result" -eq 0 ]
}

@test "999997" {
  result="$($fermat 999997)"
  [ "$result" -eq 999828 ]
}

@test "999998" {
  result="$($fermat 999998)"
  [ "$result" -eq 999990 ]
}

@test "999999" {
  result="$($fermat 999999)"
  [ "$result" -eq 999756 ]
}

@test "1000000" {
  result="$($fermat 1000000)"
  [ "$result" -eq 999996 ]
}

@test "1000001" {
  result="$($fermat 1000001)"
  [ "$result" -eq 989800 ]
}

@test "1000002" {
  result="$($fermat 1000002)"
  [ "$result" -eq 999994 ]
}

@test "1000003" {
  result="$($fermat 1000003)"
  [ "$result" -eq 0 ]
}

@test "no argument" {
  run $fermat
  [ "$status" -eq 1 ]
}

@test "many arguments" {
  run $fermat 10 lala
  [ "$status" -eq 1 ]
}

@test "bad argument" {
  run $fermat dijd
  [ "$status" -eq 2 ]
}
