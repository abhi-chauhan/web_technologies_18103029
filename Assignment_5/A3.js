<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<!-- <pre> -->
<script>
var map = {
    "(": ")",
    "[": "]",
    "{": "}"
  }
var isTrue = (s) => {
var list1 = [];
for (var i = 0; i < s.length; i++) {
    var item = s[i];
    if (map[item]) {
        list1.push(map[item]);
    } else {
    if (item !== list1.pop()) {
        return false;
    }
    }
}
return list1.length === 0;
};

s = prompt("input string", "{{(([]))}}")
document.write(isTrue(s));

</script>
<!-- </pre> -->
</body>
</html>