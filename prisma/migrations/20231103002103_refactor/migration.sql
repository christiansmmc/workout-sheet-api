/*
  Warnings:

  - You are about to alter the column `weight` on the `client` table. The data in that column could be lost. The data in that column will be cast from `Decimal` to `DoublePrecision`.
  - You are about to alter the column `height` on the `client` table. The data in that column could be lost. The data in that column will be cast from `Decimal` to `DoublePrecision`.
  - You are about to alter the column `weight` on the `client_history` table. The data in that column could be lost. The data in that column will be cast from `Decimal` to `DoublePrecision`.
  - You are about to alter the column `height` on the `client_history` table. The data in that column could be lost. The data in that column will be cast from `Decimal` to `DoublePrecision`.

*/
-- DropForeignKey
ALTER TABLE "client" DROP CONSTRAINT "client_user_id_fkey";

-- DropForeignKey
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_client_id_fkey";

-- DropForeignKey
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_exercise_id_fkey";

-- DropForeignKey
ALTER TABLE "client_history" DROP CONSTRAINT "client_history_client_id_fkey";

-- DropForeignKey
ALTER TABLE "workout" DROP CONSTRAINT "workout_client_id_fkey";

-- DropForeignKey
ALTER TABLE "workout_exercise" DROP CONSTRAINT "workout_exercise_exercise_id_fkey";

-- DropForeignKey
ALTER TABLE "workout_exercise" DROP CONSTRAINT "workout_exercise_workout_id_fkey";

-- AlterTable
ALTER TABLE "client" ALTER COLUMN "name" SET DATA TYPE TEXT,
ALTER COLUMN "weight" SET DATA TYPE DOUBLE PRECISION,
ALTER COLUMN "height" SET DATA TYPE DOUBLE PRECISION;

-- AlterTable
ALTER TABLE "client_history" ALTER COLUMN "weight" SET DATA TYPE DOUBLE PRECISION,
ALTER COLUMN "height" SET DATA TYPE DOUBLE PRECISION;

-- AlterTable
ALTER TABLE "exercise" ALTER COLUMN "name" SET DATA TYPE TEXT;

-- AlterTable
ALTER TABLE "users" ALTER COLUMN "email" SET DATA TYPE TEXT,
ALTER COLUMN "password" SET DATA TYPE TEXT;

-- AlterTable
ALTER TABLE "workout" ALTER COLUMN "name" SET DATA TYPE TEXT;

-- AddForeignKey
ALTER TABLE "client" ADD CONSTRAINT "client_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "client_history" ADD CONSTRAINT "client_history_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "workout" ADD CONSTRAINT "workout_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "workout_exercise" ADD CONSTRAINT "workout_exercise_workout_id_fkey" FOREIGN KEY ("workout_id") REFERENCES "workout"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "workout_exercise" ADD CONSTRAINT "workout_exercise_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "exercise"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "client_exercise_history" ADD CONSTRAINT "client_exercise_history_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "client_exercise_history" ADD CONSTRAINT "client_exercise_history_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "exercise"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
