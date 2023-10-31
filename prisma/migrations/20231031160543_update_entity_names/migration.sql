/*
  Warnings:

  - You are about to drop the `Exercise` table. If the table is not empty, all the data it contains will be lost.

*/
-- DropForeignKey
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_exercise_id_fkey";

-- DropForeignKey
ALTER TABLE "workout_exercise" DROP CONSTRAINT "workout_exercise_exercise_id_fkey";

-- DropTable
DROP TABLE "Exercise";

-- CreateTable
CREATE TABLE "exercise" (
    "id" TEXT NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "body_part" "BodyPart" NOT NULL,

    CONSTRAINT "exercise_pkey" PRIMARY KEY ("id")
);

-- AddForeignKey
ALTER TABLE "workout_exercise" ADD CONSTRAINT "workout_exercise_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "exercise"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- AddForeignKey
ALTER TABLE "client_exercise_history" ADD CONSTRAINT "client_exercise_history_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "exercise"("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
