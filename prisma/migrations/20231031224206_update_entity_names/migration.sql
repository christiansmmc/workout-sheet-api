/*
  Warnings:

  - The values [SHOULDERS,CHEST,FOREARMS,ABS,LEGS,BACK] on the enum `BodyPart` will be removed. If these variants are still used in the database, this will fail.
  - You are about to drop the column `createdAt` on the `users` table. All the data in the column will be lost.
  - You are about to drop the column `updatedAt` on the `users` table. All the data in the column will be lost.
  - Added the required column `load` to the `client_exercise_history` table without a default value. This is not possible if the table is not empty.
  - Added the required column `load` to the `workout_exercise` table without a default value. This is not possible if the table is not empty.

*/
-- AlterEnum
BEGIN;
CREATE TYPE "BodyPart_new" AS ENUM ('OMBRO', 'PEITO', 'BICEPS', 'TRICEPS', 'ANTEBRACO', 'PERNA', 'COSTAS');
ALTER TABLE "exercise" ALTER COLUMN "body_part" TYPE "BodyPart_new" USING ("body_part"::text::"BodyPart_new");
ALTER TYPE "BodyPart" RENAME TO "BodyPart_old";
ALTER TYPE "BodyPart_new" RENAME TO "BodyPart";
DROP TYPE "BodyPart_old";
COMMIT;

-- AlterTable
ALTER TABLE "client_exercise_history" ADD COLUMN     "load" DOUBLE PRECISION NOT NULL;

-- AlterTable
ALTER TABLE "users" DROP COLUMN "createdAt",
DROP COLUMN "updatedAt",
ADD COLUMN     "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- AlterTable
ALTER TABLE "workout_exercise" ADD COLUMN     "load" DOUBLE PRECISION NOT NULL;
