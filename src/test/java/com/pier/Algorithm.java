package com.pier;

import java.math.BigInteger;

/**
 * @auther zhongweiwu
 * @date 2019/1/31 18:25
 */
public class Algorithm {

    long calFib(long n){
        if (n <= 1){
            return 1;
        }
        return calFib(n-2) + calFib(n-1);
    }

    long f(int m,int n){   //m^n
        if(n==1) return m;
        long temp=f(m,n/2);
        return (n%2==0 ? 1 : m)*temp*temp;
    }

    long pow4(int a,int b){//70算不出来
        long r=1;
        long base=a;
        while(b!=0){
            if((b & 1)!=0) r*=base;
            base*=base;
            b>>=1;
        }
        return r;
    }

    public BigInteger fibonacci2(long n){
        BigInteger[] a;
        a = new BigInteger[100000001];
        a[0] = BigInteger.ZERO;
        a[1] = BigInteger.ONE;
        int i;
        for(i=2;i<n;i++)
            a[i] = a[i-1].add(a[i-2]);
        return a[i];
    }

    public long fibonacci(long n){
        if(n <= 1){
            return 1;
        }
        long n1 = 1, n2 = 1, sn = 0;
        for(long i = 0; i <=n - 2; i ++){
            sn = (n1 + n2)%10000000000000000L;
            n1 = n2;
            n2 = sn;
        }
        return (int)sn;
    }
}
