/**
 *
 */

package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 *
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:
     *
     * think of what type of keys and values would best suit the requirements
     */
    private final HashMap<String, HashMap<String, U>> followedUsers;

    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */

    public SocialNetworkUserImpl(final String firstname, final String lastName, final String username, final int userAge){
        super(firstname, lastName, username, userAge);
        this.followedUsers = new HashMap<>();
    }
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final UserImpl user){
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getAge());
        this.followedUsers = new HashMap<>();
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(final String firstname, final String lastName, final String username){
        super(firstname, lastName, username, -1);
        this.followedUsers = new HashMap<>();
    }

    /*
     * [METHODS]
     *
     * Implements the methods below
     */
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        HashMap<String, U> couple = new HashMap<>();
        if (this.followedUsers.containsKey(circle)){
            couple = this.followedUsers.get(circle);
        }

        couple.put(user.getUsername(), user);
        for (final U followers:this.getFollowedUsers()){
            if(user.equals(followers)){
                return false;
            }
        }

        this.followedUsers.put(circle, couple);
        return true;
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        if (this.followedUsers.containsKey(groupName)){
            final Map<String, U> innerMap = followedUsers.get(groupName);
            final Collection<U> followed = new ArrayList<>(innerMap.values());
            return followed;
        }
        final Collection<U> empty= new ArrayList<>();
        return empty;
    }

    @Override
    public List<U> getFollowedUsers() {
        final List<U> users = new ArrayList<>();
        if(!this.followedUsers.isEmpty()){
                for (final Map<String, U> group : this.followedUsers.values()) {
                users.addAll((group.values()));
            }
        }
        return users;
        }
}
