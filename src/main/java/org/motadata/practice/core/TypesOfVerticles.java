package org.motadata.practice.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TypesOfVerticles extends AbstractVerticle
{
    static Vertx vertx = Vertx.vertx();

    static DeploymentOptions options = new DeploymentOptions().setThreadingModel(ThreadingModel.VIRTUAL_THREAD);

    @Override
    public void start() throws Exception
    {
        System.out.println("Status: " + vertx.deployVerticle(TypesOfVerticles.class.getName(), options));
    }

    public static void main(String[] args) throws IOException
    {
        TypesOfVerticles verticle = new TypesOfVerticles();

        vertx.deployVerticle(verticle);

        System.out.println("1");

        vertx.deployVerticle(verticle);

//        System.out.println("DId - "+verticle.deploymentID());

//        vertx.undeploy(verticle.deploymentID()).onComplete(res->{
//            if(res.succeeded())
//                System.out.println("Success");
//
//            else
//                System.out.println("Failure");
//        });

//        String command="ping -c 4 google.com";
//
//        ProcessBuilder builder= new ProcessBuilder(command.split("\\s+"));
//
//        Process process = builder.start();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
    }
}
