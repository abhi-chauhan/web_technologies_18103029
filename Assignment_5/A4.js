<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<pre>
<script>
    
    var n = 10;
    
    if(n<3){
        document.write("Too small")
    }
    else if(n<=5){
        christmasTree(n);
        
        for(i=1; i<=2; i++)
        {
            for(j=0; j<(n*2-4)/2; j++)
            {
                document.write(" ")
            }
            for(j=0;j<=2;j++)
            {
            document.write("0");
            }
            document.write("<br>");
        }
    }
    else{
        christmasTreePattern(n);
        tree(n);
        
    }
    
    function christmasTreePattern(n) {
        for(x=1; x<=n; x++)
        { 
            for(y=x; y<n; y++)
            {
                document.write(" ");
            }
            for(y=1; y<=(2*x-1); y++)
            {
                if(x==1){
                    document.write("*");
                }
                else{
                document.write("0");
                }
            }
        
            document.write("<br>");
        }
    }

    function tree(n) {
        for(i=1; i<=3; i++)
        {
            for(j=0; j<=(n*2-5)/2; j++)
            {
                document.write(" ")
            }
            for(j=0;j<=2;j++)
            {
            document.write("0");
            }
            document.write("<br>");
        }
    }

</script>
</pre>
</body>
</html>