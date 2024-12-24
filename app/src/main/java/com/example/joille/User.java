package com.example.joille;

import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("id")
        int id;
        String name;
        String email;
        int password;

        int usersCategories_id;
        private boolean success;
        private String message;



        public User(String name, String message) {
                this.name = name;
                this.message = message;
                this.success = success;
                this.usersCategories_id = usersCategories_id;
                this.password = password;
                this.email = email;
                this.id = id;
        }


        public int getId() {
                return id;
        }

        public String getMessage() {
                return message;
        }

        public boolean isSuccess() {
                return success;
        }

        public int getUsersCategories_id() {
                return usersCategories_id;
        }

        public int getPassword() {
                return password;
        }

        public String getEmail() {
                return email;
        }

        public String getName() {
                return name;
        }


}
