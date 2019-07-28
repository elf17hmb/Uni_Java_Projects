def ggt(a,b):
    while (a!=b):
        if a>b:
            a=a-b
        else:
            b=b-a
    return(a)

if __name__=="__main__":

    a=120
    b=30

    while (a!=b):
        if a>b:
            a=a-b
        else:
            b=b-a
        print(a)

    print()
    print(ggt(a,b))