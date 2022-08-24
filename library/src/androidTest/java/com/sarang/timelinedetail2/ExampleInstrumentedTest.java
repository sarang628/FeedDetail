package com.sarang.timelinedetail2;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 Instrumented test, which will execute on an Android device.

 @see <a href="http://d.android.com/tools/testing">Testing documentation</a> */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.sarang.timelinedetail2.test", appContext.getPackageName());

                /*TimeLineDetailActions timeLineDetailActions = new TimeLineDetailActions() {
            @Override
            public void like(Like like, OnLikeCallbackListner onLikeCallbackListner) {
                Logger.d(like);
                onLikeCallbackListner.onLike(like);
            }

            @Override
            public void releaseLike(int review_id, int user_id) {

            }

            @Override
            public void getComments(int review_id, OnCommentListCallbackListener onCommentCallbackListener) {
                ArrayList<Comment> list = new ArrayList<>();
                Comment comment = new Comment();
                comment.comment = "aaaa";
                list.add(new Comment.Builder()
                        .user(new User.Builder()
                                .userName("마포구에서 가장 귀여운 개 김쪼꼬")
                                .userId(1)
                                .profilePic("http://www.iluvcebu.com/wp-content/uploads/2015/11/Tobys-Estate-3.jpg")
                                .build())
                        .commentId(1)
                        .comment("여기 바질페스토 파스타 정말 맛있죠 닭다리살 구이도 너무 맛있고 두루 다 맛나고 좋아요ㅎㅎ 오늘의 리뷰 축하드려요!!")
                        .createDate("2 시간 전")
                        .build());

                list.add(new Comment.Builder()
                        .user(new User.Builder()
                                .userName("꽃보다 맛집 @---#")
                                .userId(2)
                                .profilePic("http://www.iluvcebu.com/wp-content/uploads/2015/11/Tobys-Estate-3.jpg")
                                .build())
                        .commentId(2)
                        .comment("파스타 천재 사장님이 만드신 파스타 한 번 맛보러 가고싶네요 :) 리뷰 잘보고 갑니다.")
                        .createDate("2 시간 전")
                        .build());
                onCommentCallbackListener.onReceive(list);
            }

            @Override
            public void addComment(Comment comment, OnCommentCallbackListener onCommentCallbackListener) {
                if (onCommentCallbackListener != null)
                    onCommentCallbackListener.onReceive(comment);

            }

            @Override
            public void deleteComment(Comment comment, OnCommentCallbackListener onCommentCallbackListener) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("삭제?")
                        .setPositiveButton("Y", (dialogInterface, i) -> {
                            if (onCommentCallbackListener != null)
                                onCommentCallbackListener.onReceive(comment);
                        })
                        .setNegativeButton("N", (dialogInterface, i) -> {

                        })
                        .show();
            }

            @Override
            public void updateComment(Comment comment) {

            }
        };*/

        /*ApiManager.getInstance().getMyReview(0, 0, new BaseCallbackListener<Review>() {
            @Override
            public void callback(Review model) {

                TimeLineDetailFragment.go(getSupportFragmentManager(), R.id.fl_test, model, timeLineDetailActions, new User.Builder()
                        .userId(1)
                        .profilePic("http://www.iluvcebu.com/wp-content/uploads/2015/11/Tobys-Estate-3.jpg")
                        .build());
            }
        });*/
    }
}
