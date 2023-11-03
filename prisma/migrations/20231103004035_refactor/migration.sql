/*
  Warnings:

  - You are about to drop the `client_exercise_history` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `client_history` table. If the table is not empty, all the data it contains will be lost.

*/
-- DropForeignKey
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_client_id_fkey";

-- DropForeignKey
ALTER TABLE "client_exercise_history" DROP CONSTRAINT "client_exercise_history_exercise_id_fkey";

-- DropForeignKey
ALTER TABLE "client_history" DROP CONSTRAINT "client_history_client_id_fkey";

-- DropTable
DROP TABLE "client_exercise_history";

-- DropTable
DROP TABLE "client_history";

-- CreateTable
CREATE TABLE "client_record" (
    "id" TEXT NOT NULL,
    "weight" DOUBLE PRECISION,
    "height" DOUBLE PRECISION,
    "date" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "client_id" TEXT NOT NULL,

    CONSTRAINT "client_record_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "client_exercise_record" (
    "id" TEXT NOT NULL,
    "load" DOUBLE PRECISION NOT NULL,
    "date" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "client_id" TEXT NOT NULL,
    "exercise_id" TEXT NOT NULL,

    CONSTRAINT "client_exercise_record_pkey" PRIMARY KEY ("id")
);

-- AddForeignKey
ALTER TABLE "client_record" ADD CONSTRAINT "client_record_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "client_exercise_record" ADD CONSTRAINT "client_exercise_record_client_id_fkey" FOREIGN KEY ("client_id") REFERENCES "client"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "client_exercise_record" ADD CONSTRAINT "client_exercise_record_exercise_id_fkey" FOREIGN KEY ("exercise_id") REFERENCES "exercise"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
