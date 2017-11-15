/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import integration.peercheck.Articles;
import integration.peercheck.Users;
import integration.peercheck.WSPeercheck;
import integration.peercheck.WSPeercheck_Service;
import java.util.List;

public class PeercheckSOAPController {
    
    private static WSPeercheck_Service service;
    private static WSPeercheck port;
    
    static {
        service = new integration.peercheck.WSPeercheck_Service();
        port = service.getWSPeercheckPort();
    }

    public static Users login(java.lang.String email, java.lang.String password) {
        return port.login(email, password);
    }

    public static Users signup(integration.peercheck.Users user) {
        return port.signup(user);
    }

    public static List<Articles> findArticleBy(integration.peercheck.ArticleCriteria criteria, java.lang.String value) {
        return port.findArticleBy(criteria, value);
    }

    public static boolean changeRol(integration.peercheck.Users user, integration.peercheck.UserRole role) {
        return port.changeRol(user, role);
    }

    public static double calculateFinalGradeToArticle(integration.peercheck.Articles article) {
        return port.calculateFinalGradeToArticle(article);
    }

    public static boolean assignReviewerToArticle(integration.peercheck.Users user, integration.peercheck.Articles article) {
        return port.assignReviewerToArticle(user, article);
    }

    public static boolean addReview(integration.peercheck.Reviews review) {
        return port.addReview(review);
    }

    public static boolean addArticle(integration.peercheck.Articles article) {
        return port.addArticle(article);
    }
}