package fr.limayrac.infovente.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class aspectDeLogging {
    @Before("execution(* fr.limayrac.infovente.controller.*.*(..))")
    public void logArguments (JoinPoint joinPoint){
        Object [] args = joinPoint.getArgs();
        String nomClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomMethode = joinPoint.getSignature().getName();
        System.out.println("1 (@Before) " + nomClasse + "." + nomMethode + "--------------");
        System.out.println("########### arguments de la méthode");

        for (int i = 0; i < args.length; i++)
        {
            System.out.println(args[i]);
        }
        System.out.println("#############");
        Arrays.stream(args).forEach(System.out::println);
    }

    @After("execution(* fr.limayrac.infovente.controller.*.*(..))")
    public void nettoyage(JoinPoint joinPoint)
    {
        String nomClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomMethode = joinPoint.getSignature().getName();
        System.out.println("5 (@After) " +nomClasse + "." + nomMethode + "-----------------");
        System.out.println("methode appelee apres execution");
    }

    @AfterThrowing(pointcut = "execution(* fr.limayrac.infovente.controller.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception)
    {
        String nomClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomMethode = joinPoint.getSignature().getName();
        System.out.println("4 (@AfterThrowing) " + nomClasse + "." + nomMethode + "--------------");
        System.out.println("message de l'exception = " + exception.getMessage());
    }

    @AfterReturning(value= "execution(* fr.limayrac.infovente.controller.*.*(..))", returning = "valeurDeRetour")
    public void logValeurDeRetour(JoinPoint joinPoint, Object valeurDeRetour)
    {
        String nomClasse = joinPoint.getSignature().getDeclaringTypeName();
        String nomMethode = joinPoint.getSignature().getName();
        System.out.println("3 (@AfterReturning) " + nomClasse + "." + nomMethode + "--------------");
        System.out.println("Valeur de retour = " + valeurDeRetour);
    }
}
