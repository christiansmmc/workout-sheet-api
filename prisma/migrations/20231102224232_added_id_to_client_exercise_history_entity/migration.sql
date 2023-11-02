/*
  Warnings:

  - The primary key for the `client_exercise_history` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The required column `id` was added to the `client_exercise_history` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.

*/
-- AlterTable
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_pkey",
ADD COLUMN     "id" TEXT NOT NULL,
ADD CONSTRAINT "client_exercise_history_pkey" PRIMARY KEY ("id");
