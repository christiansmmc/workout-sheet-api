/*
  Warnings:

  - You are about to drop the `Client` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `User` table. If the table is not empty, all the data it contains will be lost.

*/
-- CreateEnum
CREATE TYPE "BodyPart" AS ENUM ('SHOULDERS', 'CHEST', 'BICEPS', 'TRICEPS', 'FOREARMS', 'ABS', 'LEGS', 'BACK');

-- DropForeignKey
ALTER TABLE "Client" DROP CONSTRAINT "Client_user_id_fkey";

-- DropTable
DROP TABLE "Client";

-- DropTable
DROP TABLE "User";

-- CreateTable
CREATE TABLE "users" (
    "id" TEXT NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "client" (
    "id" TEXT NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "weight" DECIMAL,
    "height" DECIMAL,
    "user_id" TEXT NOT NULL,

    CONSTRAINT "client_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "client_history" (
    "id" TEXT NOT NULL,
    "weight" DECIMAL,
    "height" DECIMAL,
    "date" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "client_id" TEXT NOT NULL,

    CONSTRAINT "client_history_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "workout" (
    "id" TEXT NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "client_id" TEXT NOT NULL,

    CONSTRAINT "workout_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Exercise" (
    "id" TEXT NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "body_part" "BodyPart" NOT NULL,

    CONSTRAINT "Exercise_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "workout_exercise" (
    "workout_id" TEXT NOT NULL,
    "exercise_id" TEXT NOT NULL,

    CONSTRAINT "workout_exercise_pkey" PRIMARY KEY ("workout_id","exercise_id")
);

-- CreateTable
CREATE TABLE "client_exercise_history" (
    "date" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "client_id" TEXT NOT NULL,
    "exercise_id" TEXT NOT NULL,

    CONSTRAINT "client_exercise_history_pkey" PRIMARY KEY ("client_id","exercise_id")
);

-- CreateIndex
CREATE UNIQUE INDEX "users_email_key" ON "users"("email");

-- CreateIndex
CREATE UNIQUE INDEX "client_user_id_key" ON "client"("user_id");

-- CreateIndex
CREATE UNIQUE INDEX "client_history_client_id_key" ON "client_history"("client_id");

-- CreateIndex
CREATE UNIQUE INDEX "workout_client_id_key" ON "workout"("client_id");

-- CreateIndex
CREATE UNIQUE INDEX "workout_exercise_workout_id_key" ON "workout_exercise"("workout_id");

-- CreateIndex
CREATE UNIQUE INDEX "workout_exercise_exercise_id_key" ON "workout_exercise"("exercise_id");

-- CreateIndex
CREATE UNIQUE INDEX "client_exercise_history_client_id_key" ON "client_exercise_history"("client_id");

-- CreateIndex
CREATE UNIQUE INDEX "client_exercise_history_exercise_id_key" ON "client_exercise_history"("exercise_id");

-- AddForeignKey
ALTER TABLE "client" ADD CONSTRAINT "client_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "client_history" ADD CONSTRAINT "client_history_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "workout" ADD CONSTRAINT "workout_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "workout_exercise" ADD CONSTRAINT "workout_exercise_workout_id_fkey" FOREIGN KEY ("workout_id") REFERENCES "workout"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "workout_exercise" ADD CONSTRAINT "workout_exercise_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "Exercise"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "client_exercise_history" ADD CONSTRAINT "client_exercise_history_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "client_exercise_history" ADD CONSTRAINT "client_exercise_history_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "Exercise"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
