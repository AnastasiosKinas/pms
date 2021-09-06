package com.tasoskinas.Post.Management.System.service;

import com.tasoskinas.Post.Management.System.entity.Category;
import com.tasoskinas.Post.Management.System.entity.Comment;
import com.tasoskinas.Post.Management.System.entity.Post;
import com.tasoskinas.Post.Management.System.entity.User;
import com.tasoskinas.Post.Management.System.enums.Role;
import com.tasoskinas.Post.Management.System.repository.CategoryRepository;
import com.tasoskinas.Post.Management.System.repository.CommentRepository;
import com.tasoskinas.Post.Management.System.repository.PostRepository;
import com.tasoskinas.Post.Management.System.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitDataServiceImpl implements InitDataService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public InitDataServiceImpl(UserRepository userRepository,
                               CategoryRepository categoryRepository,
                               PostRepository postRepository,
                               CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public void createInitData() {
        if (userRepository.count() != 0
            || categoryRepository.count() != 0
            || postRepository.count() != 0
            || commentRepository.count() != 0) {
            return;
        }

        User king = userRepository.save(new User(0,
            "king",
            new BCryptPasswordEncoder().encode("12345"),
            "ELVIS",
            "PRESLEY",
            Role.ADMIN,
            null,
            null,
            null));
        User goat = userRepository.save(new User(0,
            "goat",
            new BCryptPasswordEncoder().encode("randstrhoo2"),
            "LIONEL",
            "MESSI",
            Role.SIMPLE_USER,
            null,
            null,
            null));

        Category sports = categoryRepository.save(new Category(0, king, "Sports", null, null));
        Category travelDestinations = categoryRepository.save(new Category(0, king, "Travel Destinations", null, null));
        Category food = categoryRepository.save(new Category(0, king, "Food", null, null));

        Post sportsPost1 = postRepository.save(new Post(0,
            "Football",
            king,
            "Football, better known as soccer in the US and "
            + "Canada, is the most popular "
            + "sport in the world, with an "
            + "estimated following of 4 billion fans. Like"
            + " some of the other sports on this list, the"
            + " origins of football as we know it are in "
            + "England in the 19th century, though history"
            + " points to people playing similar games as "
            + "far back as two thousand years, beginning "
            + "in China. One reason for football’s "
            + "popularity is that unlike other sports that require expensive equipment, all you need to play football"
            + " is a ball and your foot. Hence, anyone, rich or poor, can enjoy the sport. It is played all over the "
            + "world, but is particularly popular in Europe, Central and South America, and Africa.",
            sports,
            null,
            0,
            null));

        Post sportsPost2 = postRepository.save(new Post(0,
            "Cricket",
            king,
            "Cricket boasts a fan base of 2.5 billion. The game is most popular in the UK "
            + "and "
            + "some former British colonies, notably India, Pakistan and Australia. Like baseball, it involves "
            + "two teams, a bat, a large field, and scoring runs. There are significant differences, however, "
            + "including a long rectangular pitch at the center of a cricket field, where the ball is thrown to a"
            + " batsman, as opposed to a mound at the center of a baseball diamond facing a batter at home plate.",
            sports,
            null,
            0,
            null));

        Post sportsPost3 = postRepository.save(new Post(0,
            "Hockey",
            king,
            "Hockey both on ice and in a field, boasts a following of two billion people. "
            + "Field hockey is mainly played in Europe, Africa, Asia, and Australia, whereas ice hockey is "
            + "particularly popular in Canada, the US, and Northern Europe. The game involves two teams trying to"
            + " put a ball into the opposing team’s net with a hockey stick. Unlike ice hockey, field hockey "
            + "usually does not involve body contact in the form of checking.",
            sports,
            null,
            0,
            null));

        Post sportsPost4 = postRepository.save(new Post(0,
            "Tennis",
            king,
            "An estimated one billion people worldwide follow tennis, tuning in from around "
            + "the world. In tennis, players on both sides of an elongated net try to hit a ball with a racket so"
            + " that it either goes past their opponents or bounces on their opponents’ side twice to score "
            + "points. Pro tennis players, like Roger Federer and Serena Williams, are now household names around"
            + " the world.",
            sports,
            null,
            0,
            null));

        Post sportsPost5 = postRepository.save(new Post(0,
            "Volleyball",
            king,
            "Volleyball involves two teams, each on one side of a raised net, trying to "
            + "“volley” a ball onto the ground of the opposing team’s side for points. The game has a following "
            + "of 900 million people, mostly in North America and Western Europe but also in Asia, Australia, and"
            + " South America. One popular variant of the game, known as beach volleyball, is played on sand with"
            + " two people on each team, as opposed to regular volleyball, where there are normally six players "
            + "on each team.",
            sports,
            null,
            0,
            null));

        Comment firstPostComment1 = commentRepository.save(new Comment(0,
            king,
            "Football is great",
            sportsPost1,
            0,
            null));

        Comment firstPostComment2 = commentRepository.save(new Comment(0,
            goat,
            "Cricket is a competitive sport",
            sportsPost2,
            0,
            null));

        Comment firstPostComment3 = commentRepository.save(new Comment(0,
            goat,
            "we love hockey",
            sportsPost3,
            0,
            null));

        Comment firstPostComment4 = commentRepository.save(new Comment(0,
            goat,
            "Tennis is the best way for exercise",
            sportsPost4,
            0,
            null));

        Comment firstPostComment5 = commentRepository.save(new Comment(0,
            goat,
            "Volleyball is a great sport for everyone",
            sportsPost5,
            0,
            null));
    }
}
